package com.superhakce.avengers.common.utils;

import com.google.common.collect.Maps;
import com.superhakce.avengers.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/23 10:37
 * @description      验证工具类
 */
@Slf4j
@Component
public class ValidatorUtil<T> {
    @Autowired
    private Validator validator;

    public void validate(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        LinkedHashMap<String, String> errors = Maps.newLinkedHashMap();
        if (!validateResult.isEmpty()) {
            validateResult.forEach(s -> {
                errors.put(s.getPropertyPath().toString(), s.getMessage());
            });
        }
        if (MapUtils.isNotEmpty(errors)) {
            throw new ParamException(errors.toString());
        }
    }
}
