package com.superhakce.algorithm.designpatterns.decorator;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体的构件角色
 * @Date: Create in 2018/10/2 15:05
 */
public class BonusConcreteComponet implements BonusComponet{

    @Override
    public Double getBonus(String name){
        return 0.00;
    }

}
