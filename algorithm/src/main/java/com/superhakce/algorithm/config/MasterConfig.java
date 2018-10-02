package com.superhakce.algorithm.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Master 数据库配置类
 * @Date: Create in 2018/9/27 21:59
 */
@ConfigurationProperties(prefix = "mysql.datasource.master")
@Data
public class MasterConfig {

    private String url;

    private String username;

    private String password;

    private int minPoolSize;

    private int maxPoolSize;

    private int maxLifetime;

    private int borrowConnectionTimeout;

    private int loginTimeout;

    private int maintenanceInterval;

    private int maxIdleTime;

    private String testQuery;

}
