package com.superhakce.avengers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex implements BaseEnum<Integer> {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMAIL(2, "女")
    ;

    private Integer code;
    private String desc;

    public static class Convert extends BaseEnumConverter<Sex, Integer> {

    }
}
