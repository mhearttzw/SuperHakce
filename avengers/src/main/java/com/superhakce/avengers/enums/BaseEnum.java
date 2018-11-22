package com.superhakce.avengers.enums;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 16:28
 * @description      基础枚举类
 */
public interface BaseEnum<Y>{
    /**
     * 存取到数据库中的值.
     *
     * @return
     */
    Y getCode();

    /**
     * 获取到描述
     *
     * @return
     */
    String getDesc();
}
