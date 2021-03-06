package com.superhakce.universe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaServer
@EnableZuulProxy
@PropertySource({"classpath:application.properties", "classpath:application.yml"})
public class UniverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniverseApplication.class, args);
    }
}
