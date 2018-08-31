package com.superhakce.webblog.enums;

import lombok.Getter;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 状态码 0000L 成功
 * @Date: Create in 2018/6/14 19:10
 */
public enum SystemCode {

    SUCCESS("0000","成功"),
    SYSTEM_ERROR("9999", "SYSTEM_ERROR");

    @Getter
    private String code;

    @Getter
    private String msg;

    SystemCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
