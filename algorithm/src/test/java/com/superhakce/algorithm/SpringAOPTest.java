package com.superhakce.algorithm;

import com.superhakce.algorithm.practice.springaop.ForumHandler;
import com.superhakce.algorithm.practice.springaop.ForumService;
import com.superhakce.algorithm.practice.springaop.ForumServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: SpringAOP 测试
 * @Date: Create in 2018/10/2 20:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringAOPTest {

    @Test
    public void test(){
        ForumService forumService = new ForumServiceImpl();
        ForumHandler forumHandler = new ForumHandler(forumService);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(forumService.getClass().getClassLoader(),
                forumService.getClass().getInterfaces(), forumHandler);
        proxy.removeForum(1000);
        proxy.removeTopic(2000);
    }

}
