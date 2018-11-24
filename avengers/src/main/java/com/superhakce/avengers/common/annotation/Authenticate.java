package com.superhakce.avengers.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/24 20:46
 * @description      鉴权类切面注解
 */
@Retention(RetentionPolicy.RUNTIME)  // 在运行时可以通过反射获取到，JVM会读取注解，同时保存在class文件中
@Target(ElementType.METHOD)//作用于方法、不能是构造方法
public @interface Authenticate {
}
