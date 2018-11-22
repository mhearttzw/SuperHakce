package com.superhakce.avengers.service;

import com.superhakce.avengers.model.JsonResult;
import com.superhakce.avengers.model.userInfo.SignUpModel;

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
}
