package com.superhakce.avengers.model.userInfo.res;

import lombok.Data;

@Data
public class SignUserResModel {

    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 头像
     */
    private String avatar;

    /**
     * token凭证
     */
    private String token;

}
