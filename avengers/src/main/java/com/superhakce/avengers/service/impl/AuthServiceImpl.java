package com.superhakce.avengers.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.superhakce.avengers.Constants;
import com.superhakce.avengers.service.AuthService;
import com.superhakce.avengers.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangfeng 2018/8/28 21:40
 * 鉴权并返回鉴权结果
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    @Value("${authorization.url}")
    private String authUrl;

    @Value("${server.context-path}")
    private String contextPath;

    @Autowired
    private RedisService redisService;


    @Autowired
    private HttpServletRequest request;

    public String getAuthInfo() {
        String userToken = request.getHeader(Constants.AUTHORIZATION);
        log.info("接收到user_token:{}", userToken);
        if (redisService.get(userToken))

        String apiPath = contextPath + request.getServletPath();
        Map param = new HashMap();
        param.put("apiPath", apiPath);
        String resp = null;
        try {
            resp = HttpUtils.post(authUrl, param, new HashMap<String, String>() {{
                put(authorization
                        , token);
            }});
        } catch (IOException e) {
            log.error(apiPath + "鉴权失败", e);
        }
        return resp;
    }

    /**
     * 解析为返回信息 String为model,返回数据则鉴权通过,否则不通过
     */
    public AuthInfoModel hasAuth(String authInfo) {
        AuthInfoModel authInfoModel = null;
//        authInfo="{\"msg\":\"验证不通过\",\"code\":\"0000\",\"data\":{\"organizationId\":1,\"userId\":20}}";
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(authInfo);// string解析为model
        } catch (Exception e) {
            log.info("解析鉴权返回数据出错:authInfo:" + authInfo, e);
        }
        String code = jsonObject.getString("code");
        if (true || "0000".equals(code)) {
            try {
                authInfoModel = JSONObject.parseObject(jsonObject.getString("data"), AuthInfoModel.class);
            } catch (Exception e) {
                log.info("鉴权通过!但解析权限数据出错:authInfo:" + authInfo, e);
            }
        }
        return authInfoModel;
    }
}
