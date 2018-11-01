package com.superhakce.algorithm.practice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Salve 数据库配置类
 * @Date: Create in 2018/9/29 14:27
 */
@ConfigurationProperties(prefix = "mysql.datasource.salve")
@Data
public class SlaveConfig {

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
