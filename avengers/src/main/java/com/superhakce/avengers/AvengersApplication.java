package com.superhakce.avengers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaServer
@PropertySource({"classpath:application.properties", "classpath:application.yml"})
public class AvengersApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvengersApplication.class, args);
    }
}
