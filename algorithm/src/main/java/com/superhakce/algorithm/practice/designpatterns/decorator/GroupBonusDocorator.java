package com.superhakce.algorithm.practice.designpatterns.decorator;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 具体装饰角色 计算团队奖金
 * @Date: Create in 2018/10/2 15:17
 */
public class GroupBonusDocorator extends BonusDocorator{

    public GroupBonusDocorator(BonusComponet bonusComponet){
        super(bonusComponet);
    }

    @Override
    public Double getBonus(String name){
        Double prize = 0.00;
        for(Double d : TempData.achievementMap.values()){
            prize += d;
        }
        return prize * 0.01 + super.getBonus(name);
    }

}
