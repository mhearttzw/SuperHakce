package com.superhakce.flinkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
@PropertySource({"classpath:application.properties", "classpath:application.yml"})
public class FlinkafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlinkafkaApplication.class, args);
	}
}
