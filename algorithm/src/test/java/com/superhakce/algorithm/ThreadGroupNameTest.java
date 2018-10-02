package com.superhakce.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 线程组
 * @Date: Create in 2018/9/29 20:00
 */
@Slf4j
public class ThreadGroupNameTest implements Runnable{
    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");

        Thread threadOne = new Thread(threadGroup, new ThreadGroupNameTest(), "threadOne");

        Thread threadTwo = new Thread(threadGroup, new ThreadGroupNameTest(), "threadTwo");

        threadOne.start();threadTwo.start();

        log.info("threadGroup.count={}", threadGroup.activeCount());

        threadGroup.list();

    }

    @Override
    public void run(){

        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();

        while (true){

            log.info("groupAndName={}", groupAndName);

            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }

}
