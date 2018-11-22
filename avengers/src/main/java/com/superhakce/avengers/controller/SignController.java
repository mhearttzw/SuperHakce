package com.superhakce.avengers.controller;


import com.superhakce.avengers.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/10/6 18:30
 * @description      */

@Api(description = "注册登录模块")
@RestController
@RequestMapping("/sign")
public class SignController {

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public JsonResult register() {

    }
}
