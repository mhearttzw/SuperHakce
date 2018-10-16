package com.superhakce.algorithm.springaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 横切逻辑类
 * @Date: Create in 2018/10/2 20:33
 */
public class ForumHandler implements InvocationHandler{

    private Object target;

    public ForumHandler(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object obj = method.invoke(target, args);
        System.err.println("ForumHandler.invoke 代理");
        return obj;
    }

}
