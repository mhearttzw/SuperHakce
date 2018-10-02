package com.superhakce.algorithm.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Slave 实体
 * @Date: Create in 2018/9/29 15:42
 */
@Table(name = "slave")
@Data
public class Slave {

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal power;

}
