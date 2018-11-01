package com.superhakce.algorithm.practice.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 装饰器角色
 * @Date: Create in 2018/10/2 10:45
 */
public abstract class Docorator {

    private Component component = new ConcreteComponent();

    public void showAction(){
        component.showAction();
    }

    public BigDecimal showNumber(){
        return component.showNumber();
    }

}
