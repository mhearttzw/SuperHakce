package com.superhakce.avengers.enums;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 16:44
 * @description      枚举类转换类
 */
public abstract class BaseEnumConverter<X extends BaseEnum<Y>, Y> implements AttributeConverter<BaseEnum<Y>, Y> {
    private Class<X> xclazz;
    private Method valuesMethod;

    @SuppressWarnings("unchecked")
    public BaseEnumConverter() {
        this.xclazz = (Class<X>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments())[0];
        try {
            valuesMethod = xclazz.getMethod("values");
        } catch (Exception e) {
            throw new RuntimeException("can't get values method from " + xclazz);
        }
    }

    @Override
    public Y convertToDatabaseColumn(BaseEnum<Y> attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public X convertToEntityAttribute(Y dbData) {
        try {
            X[] values = (X[]) valuesMethod.invoke(null);
            for (X x : values) {
                if (x.getCode().equals(dbData)) {
                    return x;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("can't convertToEntityAttribute" + e.getMessage());
        }
        return null;
    }
}
