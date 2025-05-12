package com.ruoyi.suyuan.tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class ShipingTokenUtil {

    @Value("${shiping.appKey}")
    private String appKey;

    @Value("${shiping.appSecret}")
    private String appSecret;

    @Autowired
    private ObjectMapper objectMapper;

    public String getShipingToken() {
        String tokenUrl = "https://open.ys7.com/api/lapp/token/get";
        RestTemplate restTemplate = new RestTemplate();

        // 构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 构造请求体
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appKey", appKey);
        params.add("appSecret", appSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
           return response.getBody();
        } else {
            throw new RuntimeException("请求失败，状态码: " + response.getStatusCode());
        }
    }
    public String getEzopen(String accessToken, String deviceSerial) {
        String ezopenUrl = "https://open.ys7.com/api/lapp/v2/live/address/get";
        RestTemplate restTemplate = new RestTemplate();

        // 构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 构造请求体
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accessToken", accessToken);
        params.add("deviceSerial", deviceSerial);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.postForEntity(ezopenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
           return  response.getBody();
        } else {
            throw new RuntimeException("请求失败，状态码: " + response.getStatusCode());
        }
    }

}
