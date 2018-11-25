package com.superhakce.avengers.service;

import com.superhakce.avengers.model.JsonResult;
import com.superhakce.avengers.model.common.AuthInfoModel;
import com.superhakce.avengers.model.userInfo.req.SignUpModel;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 17:13
 * @description      用户登录实现类
 */
public interface SignUserService {

    /**
     * 用户注册
     * @param signUpModel
     * @return
     */
    JsonResult register(SignUpModel signUpModel);

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    JsonResult sendMsg(String phone);

    /**
     * 短信验证码登录
     * @param phone     手机号
     * @param msgCode   短信验证码
     * @return
     */
    JsonResult smsLogin(String phone, String msgCode);

    /**
     * 用户名密码登录
     * @param username      用户名
     * @param password      密码
     * @return
     */
    JsonResult usernameLogin(String username, String password);

    /**
     * 用户登出
     * @param authInfoModel     鉴权model
     * @return
     */
    JsonResult signOut(AuthInfoModel authInfoModel);

    /**
     * 用户修改密码
     * @param authInfoModel
     * @param oldPwd
     * @param newPwd
     * @return
     */
    JsonResult passwordChange(AuthInfoModel authInfoModel, String oldPwd, String newPwd);
}
