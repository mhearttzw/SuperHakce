package com.superhakce.algorithm.designpatterns.decorator;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体装饰角色 计算累计奖金
 * @Date: Create in 2018/10/2 15:14
 */
public class SumBonusDocorator extends BonusDocorator{

    public SumBonusDocorator(BonusComponet bonusComponet){
        super(bonusComponet);
    }

    @Override
    public Double getBonus(String name){
        Double prize = 1000000 * 0.001 + super.getBonus(name);
        return prize;
    }

}
