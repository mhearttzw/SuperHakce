package com.superhakce.algorithm;

import com.superhakce.algorithm.designpatterns.decorator.BonusComponet;
import com.superhakce.algorithm.designpatterns.decorator.BonusConcreteComponet;
import com.superhakce.algorithm.designpatterns.decorator.BonusDocorator;
import com.superhakce.algorithm.designpatterns.decorator.Docorator;
import com.superhakce.algorithm.designpatterns.decorator.GroupBonusDocorator;
import com.superhakce.algorithm.designpatterns.decorator.MonthBonusDocorator;
import com.superhakce.algorithm.designpatterns.decorator.SumBonusDocorator;
import com.superhakce.algorithm.designpatterns.decorator.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 装饰器模式测试
 * @Date: Create in 2018/10/2 11:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DecoratorPatternTest {

    @Test
    public void test(){
        Docorator docorator = new Teacher();
        docorator.showAction();
        docorator.showNumber();
    }

    @Test
    public void testBnous(){

        BonusComponet bonusComponetBase = new BonusConcreteComponet();//计算基本奖金

        BonusDocorator bonusDocoratorMonth = new MonthBonusDocorator(bonusComponetBase);//计算月奖金

        BonusDocorator bonusDocoratorSum = new SumBonusDocorator(bonusDocoratorMonth);//计算累计奖金

        BonusDocorator bonusDocoratorGroup = new GroupBonusDocorator(bonusDocoratorSum);//计算团队奖金

        Double prizeLiqin = bonusDocoratorSum.getBonus("李沁");

        Double prizeLiuYiFei = bonusDocoratorSum.getBonus("刘亦菲");

        Double prizeHeQingJiang = bonusDocoratorGroup.getBonus("贺庆江");

        System.err.println("prizeLiqin = " + prizeLiqin + " prizeLiuYiFei = " + prizeLiuYiFei + " prizeHeQingJiang = " + prizeHeQingJiang);

    }

}
