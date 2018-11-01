package com.superhakce.algorithm.practice.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Master 实体
 * @Date: Create in 2018/9/29 15:40
 */
@Table(name = "master")
@Data
public class Master {

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal power;

}
