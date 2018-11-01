package com.superhakce.algorithm.practice.designpatterns.decorator;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 装饰角色
 * @Date: Create in 2018/10/2 15:06
 */
public abstract class BonusDocorator implements BonusComponet{

    private BonusComponet bonusComponet;

    BonusDocorator(BonusComponet bonusComponet){

        this.bonusComponet = bonusComponet;

    }

    @Override
    public Double getBonus(String name){
        return bonusComponet.getBonus(name);
    }

}
