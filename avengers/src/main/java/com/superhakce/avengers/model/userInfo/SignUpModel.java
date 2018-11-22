package com.superhakce.avengers.model.userInfo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 12:18
 * @description      用户注册model
 */
@Data
public class SignUpModel {

    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String nickname;
}
