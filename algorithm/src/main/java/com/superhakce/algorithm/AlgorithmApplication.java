package com.superhakce.algorithm;

import com.superhakce.algorithm.config.MasterConfig;
import com.superhakce.algorithm.config.SlaveConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@PropertySource({"classpath:application.properties", "classpath:application.yml"})
@EnableConfigurationProperties(value = { MasterConfig.class, SlaveConfig.class})
public class AlgorithmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmApplication.class, args);
	}
}
