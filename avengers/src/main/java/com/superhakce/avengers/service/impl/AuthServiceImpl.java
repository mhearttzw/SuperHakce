package com.superhakce.avengers.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.superhakce.avengers.Constants;
import com.superhakce.avengers.entity.SignUser;
import com.superhakce.avengers.enums.BusinessCode;
import com.superhakce.avengers.exception.ParamException;
import com.superhakce.avengers.model.common.AuthInfoModel;
import com.superhakce.avengers.respository.SignUserRepository;
import com.superhakce.avengers.service.AuthService;
import com.superhakce.avengers.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private SignUserRepository signUserRepository;


    public AuthInfoModel getAuthInfo() {
        String userToken = request.getHeader(Constants.AUTHORIZATION);
        log.info("接收到user_token:{}", userToken);
        String userId = redisService.get(userToken);
        if (StringUtils.isEmpty(userId)) {
            throw new ParamException("user_token不正确");
        }
        Optional<SignUser> userOptional = signUserRepository.findById(Long.valueOf(userId));

        userOptional.orElseThrow(() ->
                new ParamException("id为" + userId + "的用户不存在！")
        );

        AuthInfoModel authInfoModel = new AuthInfoModel();
        userOptional.ifPresent(t ->

        {
            BeanUtils.copyProperties(userOptional.get(), authInfoModel);
            authInfoModel.setUserId(t.getId());
            authInfoModel.setUserToken(userToken);
        });
        return authInfoModel;
    }

}
