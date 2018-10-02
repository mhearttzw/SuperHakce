package com.superhakce.algorithm;

import com.superhakce.algorithm.designpatterns.decorator.Docorator;
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

}
