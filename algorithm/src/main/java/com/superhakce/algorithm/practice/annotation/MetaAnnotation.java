package com.superhakce.algorithm.practice.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 元注解
 * @Date: Create in 2018/9/28 11:10
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetaAnnotation {
}
