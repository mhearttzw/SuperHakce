package com.superhakce.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 守护线程
 * @Date: Create in 2018/9/29 20:13
 */
@Slf4j
public class DaemonThreadTest implements Runnable{

    @Override
    public void run(){
        while (true){
            log.info("I am alive!");
            try {
                Thread.sleep(1000L);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(new DaemonThreadTest());

        thread.setDaemon(true);

        thread.start();

        Thread.sleep(2000L);

        log.info("main is alive!");

    }

}
