package com.superhakce.avengers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Feign 接口
 * @Date: Create in 2018/8/15 15:02
 */
@FeignClient(value = "webblog")
public interface HelloWorldFeign {
    @RequestMapping(value = "/api/webblog/hello",method = RequestMethod.GET)
    String sayHelloFromClientOne(@RequestParam(value = "name") String name);
}
