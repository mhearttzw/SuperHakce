package com.superhakce.webblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 控制器
 * @Date: Create in 2018/8/15 15:28
 */
@RestController
@RequestMapping("/api/webblog")
public class HelloWorldController {

    @GetMapping("/hello")
    public String home(@RequestParam(value = "name") String name) {
        return "hello " + name;
    }

}
