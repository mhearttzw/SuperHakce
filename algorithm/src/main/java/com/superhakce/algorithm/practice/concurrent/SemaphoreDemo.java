package com.superhakce.algorithm.practice.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 信号量示例
 * @Date: Create in 2018/11/7 12:05
 */
public class SemaphoreDemo implements Runnable{

    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try{
            semaphore.acquire();
            Thread.sleep(4000L);
            System.out.println(Thread.currentThread().getId() + " done.");
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for(int i = 0;i < 20;i ++){
            executorService.submit(semaphoreDemo);
        }
    }

}
