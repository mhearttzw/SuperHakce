package com.superhakce.algorithm.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体的构建角色
 * @Date: Create in 2018/10/2 10:39
 */
public class ConcreteComponent implements Component{

    private BigDecimal number = new BigDecimal(100);

    private String action = "YES, This action is decision By SuperHakce";

    @Override
    public void showAction(){
        System.out.println(action);
    }

    @Override
    public BigDecimal showNumber(){
        return number;
    }

}
