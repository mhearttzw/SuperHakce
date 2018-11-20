package com.superhakce.algorithm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 获取服务列表
 * @Date: Create in 2018/11/20 15:30
 */
@RestController
@Configuration
public class InvokerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public List<ServiceInstance> router(){
        return getServiceInstance();
    }

    private List<ServiceInstance> getServiceInstance(){
        List<String> ids = discoveryClient.getServices();
        List<ServiceInstance> result = new ArrayList<>();
        for(String id : ids){
            result.addAll(discoveryClient.getInstances(id));
        }
        return result;
    }

}
