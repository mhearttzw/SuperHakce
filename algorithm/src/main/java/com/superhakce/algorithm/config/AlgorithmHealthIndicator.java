package com.superhakce.algorithm.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 健康自检
 * @Date: Create in 2018/11/6 17:21
 */
@Component
public class AlgorithmHealthIndicator implements HealthIndicator {

    @Override
    public Health health(){
        return new Health.Builder(Status.UP).build();
    }

}
