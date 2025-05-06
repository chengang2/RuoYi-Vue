package com.ruoyi.common.utils.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class AliyunSmsUtil {

    @Value("${sms.regionId}")
    private  String regionId;
    @Value("${sms.accessKeyId}")
    private  String accessKeyId;
    @Value("${sms.accessKeySecret}")
    private  String accessKeySecret;
    @Value("${sms.signName}")
    private  String signName;
    @Value("${sms.templateCode}")
    private  String templateCode;

    // 你可以把这些常量也放到这里
    private static final String CODE_KEY_PREFIX = "sy:login:code:";
    private static final String CODE_TS_KEY_PREFIX = "sy:login:code:ts:";
    private static final String CODE_HOUR_KEY_PREFIX = "sy:login:code:hour:";
    private static final String CODE_DAY_KEY_PREFIX = "sy:login:code:day:";

    private static final long CODE_EXPIRE_MINUTES = 5;
    private static final long SEND_CODE_INTERVAL_SECONDS = 60;
    private static final int MAX_FIVE_MINUTES_SEND_COUNT = 2;
    private static final int MAX_HOUR_SEND_COUNT = 10;
    private static final int MAX_DAILY_SEND_COUNT = 30;

    @Autowired
    private RedisCache redisCache;

    // 生成4位随机验证码
    public  String createCaptcha() {
        Random random = new Random();
        int code = random.nextInt(10000);
        return String.format("%04d", code);
    }

    // 发送短信
    public String sendSms(String phoneNum) {
        String randomNum = createCaptcha();

        DefaultProfile profile = DefaultProfile.getProfile(
                regionId, // 地域ID
                accessKeyId,
                accessKeySecret
        );
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNum);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + randomNum + "\"}");

        SendSmsResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new RuntimeException("短信发送失败: " + e);
        }
        System.out.println("response = " + response);
        if ("OK".equals(response.getCode())) {
            return randomNum;
        } else {
            throw new RuntimeException("短信发送失败: " + response.getMessage());
        }
    }

    public AjaxResult sendSmsWithLimit(String phone) {
        long now = System.currentTimeMillis();
        // 1. 1分钟内只能发一次
        String codeKey = CODE_KEY_PREFIX + phone;
        String lastCode = redisCache.getCacheObject(codeKey);
        if (lastCode != null) {
            Long ttl = redisCache.getExpire(codeKey);
            if (ttl != null && (CODE_EXPIRE_MINUTES * 60 - ttl) < SEND_CODE_INTERVAL_SECONDS) {
                return AjaxResult.error("请勿频繁获取验证码，请稍后再试");
            }
        }

        // 2. 5分钟内最多2次
        String tsKey = CODE_TS_KEY_PREFIX + phone;
        List<Long> tsList = redisCache.getCacheList(tsKey);
        tsList = tsList == null ? new ArrayList<>() : tsList;
        tsList.removeIf(ts -> now - ts > 5 * 60 * 1000);
        if (tsList.size() >= MAX_FIVE_MINUTES_SEND_COUNT) {
            return AjaxResult.error("5分钟内最多只能获取" + MAX_FIVE_MINUTES_SEND_COUNT + "次验证码");
        }

        // 3. 1小时内最多10次
        String hourKey = CODE_HOUR_KEY_PREFIX + phone;
        Integer hourCount = redisCache.getCacheObject(hourKey);
        hourCount = hourCount == null ? 0 : hourCount;
        if (hourCount >= MAX_HOUR_SEND_COUNT) {
            return AjaxResult.error("1小时内最多只能获取" + MAX_HOUR_SEND_COUNT + "次验证码");
        }

        // 4. 1天内最多30次
        String dayKey = CODE_DAY_KEY_PREFIX + phone;
        Integer dayCount = redisCache.getCacheObject(dayKey);
        dayCount = dayCount == null ? 0 : dayCount;
        if (dayCount >= MAX_DAILY_SEND_COUNT) {
            return AjaxResult.error("24小时内最多只能获取" + MAX_DAILY_SEND_COUNT + "次验证码");
        }

        // 5. 生成验证码并发送
        String code = sendSms(phone); // 你原有的短信发送方法
        redisCache.setCacheObject(codeKey, code, (int) CODE_EXPIRE_MINUTES, TimeUnit.MINUTES);

        // 6. 记录发送时间戳
        tsList.add(now);
        redisCache.deleteObject(tsKey);
        redisCache.setCacheList(tsKey, tsList);
        redisCache.expire(tsKey, 5 * 60);

        // 7. 记录小时/天计数
        redisCache.setCacheObject(hourKey, hourCount + 1, 1, TimeUnit.HOURS);
        redisCache.setCacheObject(dayKey, dayCount + 1, 1, TimeUnit.DAYS);

        return AjaxResult.success("验证码发送成功");
    }
    // 获取验证码
    public String getSmsCode(String phone) {
        return redisCache.getCacheObject(CODE_KEY_PREFIX + phone);
    }

    // 删除验证码
    public void deleteSmsCode(String phone) {
        redisCache.deleteObject(CODE_KEY_PREFIX + phone);
    }
}
