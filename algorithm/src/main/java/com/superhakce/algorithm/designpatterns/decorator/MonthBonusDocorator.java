package com.superhakce.algorithm.designpatterns.decorator;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体装饰角色 计算月奖金
 * @Date: Create in 2018/10/2 15:11
 */
public class MonthBonusDocorator extends BonusDocorator{

    public MonthBonusDocorator(BonusComponet bonusComponet){
        super(bonusComponet);
    }

    @Override
    public Double getBonus(String name){
        Double prize = TempData.achievementMap.get(name) * 0.03 + super.getBonus(name);
        return prize;
    }

}
