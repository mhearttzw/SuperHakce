package com.superhakce.avengers.service;


import com.superhakce.avengers.model.common.AuthInfoModel;

/**
 * @author jiangfeng 2018/8/28 21:40
 * 鉴权并返回鉴权结果
 */
public interface AuthService {

    /**
     * 用户鉴权
     * @return
     */
    public AuthInfoModel getAuthInfo();

}
