package com.superhakce.avengers.controller;


import com.superhakce.avengers.common.utils.ValidatorRegUtil;
import com.superhakce.avengers.model.JsonResult;
import com.superhakce.avengers.model.userInfo.SignUpModel;
import com.superhakce.avengers.service.SignUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/23 20:37
 * @description      用户注册/登录模块
 */

@Api(description = "注册登录模块")
@RestController
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private SignUserService signUserService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public JsonResult register(SignUpModel signUpModel) {
        return signUserService.register(signUpModel);
    }

    @ApiOperation(value = "发送短信验证码")
    @PostMapping(value = "/msgSend")
    public JsonResult msgSend(String phone) {
        return signUserService.sendMsg(phone);
    }

    @ApiOperation(value = "用户验证码登录/注册")
    @PostMapping(value = "/sms/login")
    public JsonResult smsLogin(String phone, String msgCode) {
        return signUserService.smsLogin(phone, msgCode);
    }

    @ApiOperation(value = "用户名密码登录")
    @PostMapping(value = "/username/login")
    public JsonResult phoneLogin(String username, String password) {
        return signUserService.usernameLogin(username, password);
    }

    @ApiOperation(value = "用户登出")
    @PostMapping(value = "/signOut")
    public JsonResult signOut() {

    }
}
