package com.superhakce.webblog.model.common;

import com.alibaba.fastjson.JSON;
import com.superhakce.webblog.enums.SystemCode;
import lombok.Data;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 统一返回Model
 * @Date: Create in 2018/6/7 10:53
 */
@Data
public class ResponseModel<T> {

    private String code;
    private String msg;
    private T data;


    public ResponseModel(){
    }


    public ResponseModel(String code, String msg, T data){
        this.msg = msg;this.code = code;this.data = data;
    }

    public ResponseModel(SystemCode systemCode){
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
    }

    public ResponseModel(SystemCode systemCode, String reason){
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg() + reason;
    }

    public ResponseModel(SystemCode systemCode, T data){
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
        this.data = data;
    }

    public ResponseModel set(SystemCode systemCode, T data){
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
        this.data = data;
        return this;
    }

    public ResponseModel set(SystemCode systemCode){
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
        return this;
    }


    public T getData() {
        return data;
    }

    public ResponseModel setData(T data) {
        this.data = data;
        return this;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public static ResponseModel buildErrorResponse(String msg, String code){
        return new ResponseModel(code,msg,null);
    }

}
