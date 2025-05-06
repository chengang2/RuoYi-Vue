package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ruoyi.common.core.domain.model.SmsLoginBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.sms.AliyunSmsUtil;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private AliyunSmsUtil aliyunSmsUtil;
    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(@RequestBody SmsLoginBody smsLoginBody) {
        String phone = smsLoginBody.getPhone();
        //判断手机号是否存在
        SysUser user = userService.selectUserByPhone(phone);
        if (user == null) {
            return AjaxResult.error("手机号不存在");
        }
        return aliyunSmsUtil.sendSmsWithLimit(phone);
    }


    @PostMapping("/smsLogin")
    public AjaxResult smsLogin(@RequestBody SmsLoginBody smsLoginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 1. 校验验证码
        String phone = smsLoginBody.getPhone();
        String code = smsLoginBody.getCode();
        String cacheCode = aliyunSmsUtil.getSmsCode(phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            return AjaxResult.error("验证码错误或已过期");
        }
        // 校验通过后，删除验证码
        aliyunSmsUtil.deleteSmsCode(phone);
        // 2. 查询用户
        SysUser user = userService.selectUserByPhone(phone);
        System.out.println("user = " + user);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }
        // 3. 构造LoginUser
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        System.out.println("loginUser = " + loginUser);
        // 4. 生成token
        String token = tokenService.createToken(loginUser);
        System.out.println("token = " + token);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }



    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        if (!loginUser.getPermissions().equals(permissions))
        {
            loginUser.setPermissions(permissions);
            tokenService.refreshToken(loginUser);
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
