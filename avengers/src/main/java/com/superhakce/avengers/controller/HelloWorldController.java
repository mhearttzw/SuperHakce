package com.superhakce.avengers.controller;

import com.superhakce.avengers.feign.HelloWorldFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 控制器
 * @Date: Create in 2018/8/15 15:06
 */
@RestController
@RequestMapping("/api/avengers")
public class HelloWorldController {

    @Autowired
    private HelloWorldFeign helloWorldFeign;

    @GetMapping(value = "/hello")
    public String sayHi(@RequestParam String name) {
        return helloWorldFeign.sayHelloFromClientOne(name);
    }
}
