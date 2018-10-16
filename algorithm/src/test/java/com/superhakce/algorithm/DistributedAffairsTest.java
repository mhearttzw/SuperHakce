package com.superhakce.algorithm;

import com.superhakce.algorithm.service.TestAtomikos;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 分布式事务，分布式锁
 * @Date: Create in 2018/9/4 18:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DistributedAffairsTest {

    @Autowired
    private TestAtomikos testAtomikos;

    @Test
    public void test(){
        testAtomikos.test();
    }

}
