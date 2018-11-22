package com.superhakce.avengers.exception;

import com.superhakce.avengers.enums.BusinessCode;
import lombok.Data;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 23:15
 * @description      参数错误异常
 */
@Data
public class ParamException extends RuntimeException {

    /**
     * //错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String msg;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(BusinessCode businessCode) {
        super(businessCode.getMsg());
        this.code = businessCode.getCode();
        this.msg = businessCode.getCode();
    }
}
