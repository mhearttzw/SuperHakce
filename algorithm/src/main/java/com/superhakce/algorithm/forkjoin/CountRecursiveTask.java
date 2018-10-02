package com.superhakce.algorithm.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: RecursiveTask ：用于有返回结果的任务
 * @Date: Create in 2018/9/30 11:19
 */
@Slf4j
public class CountRecursiveTask extends RecursiveTask{

    private static final Integer THRESHOLD = 2;//阈值

    private Integer start;//计算开始未知

    private Integer end;//计算结束未知

    public CountRecursiveTask(Integer start, Integer end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute(){
        log.info("当前线程名称name={}", Thread.currentThread().getName());
        Integer result = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute){
            for(Integer i = start; i <= end; i ++){
                result += i;
            }
            try{
                Thread.sleep(1000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {//如果任务大于阀值，就分裂成两个子任务计算
            Integer middle = (start + end) / 2;
            CountRecursiveTask leftCount = new CountRecursiveTask(start, middle);
            CountRecursiveTask rightCount = new CountRecursiveTask(middle + 1, end);
            //执行子任务
            leftCount.fork();rightCount.fork();
            //等待子任务执行完，并得到其结果
            Integer leftSum = (Integer) leftCount.join();
            Integer rightSum = (Integer) rightCount.join();
            result = leftSum + rightSum;
        }
        return result;
    }

}
