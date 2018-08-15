package com.superhakce.webblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@PropertySource({"classpath:application.properties", "classpath:application.yml"})
public class WebblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebblogApplication.class, args);
    }
}
