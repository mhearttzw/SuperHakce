package com.superhakce.avengers.model.common;

import lombok.Data;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/24 20:50
 * @description      用户鉴权model,响应类
 */
@Data
public class AuthInfoModel {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户token
     */
    private String userToken;

    private String phone;//当前登录人的电话

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    private String email;//当前登录人的邮箱


}
