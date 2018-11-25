package com.superhakce.avengers.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.superhakce.avengers.common.utils.ExceptionUtil;
import com.superhakce.avengers.enums.BusinessCode;
import com.superhakce.avengers.exception.ParamException;
import com.superhakce.avengers.exception.ThirdCallFailException;
import com.superhakce.avengers.model.JsonResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;


/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 23:25
 * @description      全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandingController {
    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult paramException(FeignException e) {
        log.error("异常信息 ex={}", ExceptionUtil.getStackTrace(e));
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(BusinessCode.FEIGN_FAIL.getCode());
        jsonResult.setMsg(BusinessCode.FEIGN_FAIL.getMsg());
        return jsonResult;
    }

    @ExceptionHandler(value = ParamException.class)
    public JsonResult handleParamException(ParamException pe) {
        return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT, pe.getMessage());
//        return new JsonResult(pe.getBusinessCode());
    }

    @ExceptionHandler(value = Exception.class)
    public JsonResult handleException(Exception e) {
        log.error("系统出现错误", e);
        return new JsonResult(BusinessCode.FAILED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult paramException(HttpRequestMethodNotSupportedException e) {
        return new JsonResult(e.getMessage(), "405");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult paramException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();
        log.info("json 转换异常捕获 ex={}", e.getMessage());
        if (cause instanceof JsonMappingException) {
            List<JsonMappingException.Reference> paths = ((JsonMappingException) cause).getPath();
            String name = paths.get(paths.size() - 1).getFieldName();
            return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT, "!字段: " + name + " 有误!");
        }
        return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public JsonResult paramException(InvalidFormatException e) {
        log.info("json 转换异常捕获 ex={}", e.getMessage());
        List<JsonMappingException.Reference> paths = e.getPath();
        String name = paths.get(paths.size() - 1).getFieldName();
        return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT, "!字段: " + name + " 有误!");
    }

    // 类转换异常捕获,包括枚举
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object paramException(MethodArgumentTypeMismatchException e) {
        log.info("转换异常捕获 ex={}", e.getMessage());
        return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT, "!字段:" + e.getName() + " 有误!");
    }

    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        fieldErrors.forEach(fieldError -> sb.append(fieldError.getField()).append(" "));
        log.info("转换异常捕获 ex={}", e.getMessage());
        return new JsonResult(BusinessCode.PARAMETER_IS_INCORRECT, "!字段:" + sb.toString() + " 有误!");
    }

    @ExceptionHandler(ThirdCallFailException.class)
    public Object validation(ThirdCallFailException e) {
        log.info("第三方服务调用失败,Exception:{}", e.getMessage());
        return new JsonResult(BusinessCode.FAILED.getCode(), e.getMessage());
    }
}
