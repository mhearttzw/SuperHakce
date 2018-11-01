package com.superhakce.algorithm.practice.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description:
 * @Date: Create in 2018/10/31 12:50
 */
public class ReenterLock implements Runnable{

    public ReenterLock(int lock) {
        this.lock = lock;
    }

    int lock;

    private static ReentrantLock lock1 = new ReentrantLock();

    private static ReentrantLock lock2 = new ReentrantLock();

    @Override
    public void run(){
        if(lock == 1){
            try{
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
                System.out.printf("Thread One Done");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
            }
            System.out.printf("线程退出");
        }else {
            try{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
                System.out.printf("Thread Two Done");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
            }
            System.out.printf("线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLock reenterLock1 = new ReenterLock(1);
        ReenterLock reenterLock2 = new ReenterLock(2);
        Thread thread1 = new Thread(reenterLock1);
        Thread thread2 = new Thread(reenterLock2);
        thread1.start();thread2.start();
        Thread.sleep(5000);
        thread2.interrupt();
    }


}
