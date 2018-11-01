package com.superhakce.algorithm.practice.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体装饰器
 * @Date: Create in 2018/10/2 11:10
 */
public class Teacher extends Docorator{

    public void showAction(){
        System.out.println("装饰器模式和代理模式的区别，装饰器接口不会变化，代理模式接口可能会调整");
        super.showAction();
    }

    public BigDecimal showNumber(){
        return super.showNumber().abs();
    }

}

