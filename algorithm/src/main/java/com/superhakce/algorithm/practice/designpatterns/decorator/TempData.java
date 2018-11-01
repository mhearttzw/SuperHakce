package com.superhakce.algorithm.practice.designpatterns.decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 临时数据
 * @Date: Create in 2018/10/2 14:59
 */
public class TempData {

    public static Map<String, Double> achievementMap = new HashMap<>();

    static {
        achievementMap.put("李沁", 10000.0);
        achievementMap.put("刘亦菲", 20000.0);
        achievementMap.put("贺庆江", 30000.0);
    }

}
