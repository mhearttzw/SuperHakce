package com.superhakce.avengers.exception;

/**
 * 第三方服务调用失败异常
 */
public class ThirdCallFailException extends RuntimeException {
    private String code;//错误码
    private String msg;//错误信息

    public ThirdCallFailException() {
    }
    public ThirdCallFailException(String message) {
        super(message);
        this.msg = message;
    }

    public ThirdCallFailException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
