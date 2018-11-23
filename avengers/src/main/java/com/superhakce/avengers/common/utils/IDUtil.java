package com.superhakce.avengers.common.utils;

import com.superhakce.avengers.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/23 12:19
 * @description      id生成工具类
 */
@Component
public class IDUtil {

    @Autowired
    private RedisService redisService;

    /**
     * @param prefix 前缀
     * @description 生成系统表id
     * @author zhangyanglei
     * @date 2017/9/4 15:27
     */
    public String genSysId(String prefix, String format) {
        long number = redisService.incr(prefix);
        String numberString = prefix + String.format(format, number);
        return numberString;
    }

    /**
     * 生成指定位数的随机整数
     * @param number   位数
     * @return 随机整数
     */
    public static int random(int number) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, number - 1));
    }

    /**
     * 获取UUID
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
