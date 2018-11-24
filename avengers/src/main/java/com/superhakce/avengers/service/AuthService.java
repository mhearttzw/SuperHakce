package com.superhakce.avengers.service;


import com.superhakce.avengers.model.common.AuthInfoModel;

/**
 * @author jiangfeng 2018/8/28 21:40
 * 鉴权并返回鉴权结果
 */
public interface AuthService {


    public String getAuthInfo();

    /**
     * 解析为返回信息 String为model,返回数据则鉴权通过,否则不通过
     */
    public AuthInfoModel hasAuth(String authInfo);
}
