package com.superhakce.algorithm;

import com.superhakce.algorithm.practice.forkjoin.CountRecursiveTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Fork/Join 框架学习
 * @Date: Create in 2018/9/30 11:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ForkJoinTest {

    @Test
    public void testCountRecursiveTask() throws InterruptedException, ExecutionException{
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountRecursiveTask countRecursiveTask = new CountRecursiveTask(1, 8);
        Future result = forkJoinPool.submit(countRecursiveTask);
        log.info("result.get()={}", result.get());
    }

}
