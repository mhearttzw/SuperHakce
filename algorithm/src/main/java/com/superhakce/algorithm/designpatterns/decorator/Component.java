package com.superhakce.algorithm.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 抽象构建角色
 * @Date: Create in 2018/10/2 10:37
 */
public interface Component {

    void showAction();

    BigDecimal showNumber();

}
