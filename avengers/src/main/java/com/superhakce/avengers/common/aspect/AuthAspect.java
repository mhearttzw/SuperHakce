package com.superhakce.avengers.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.superhakce.avengers.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/24 20:46
 * @description      鉴权切面类
 */
@Component
@Aspect
@Slf4j
public class AuthAspect {

    @Pointcut("@annotation(com.superhakce.avengers.common.annotation)")
    public void setAuthenticatePointCut() {
    }
    @Autowired
    AuthService authService;

    @Around("setAuthenticatePointCut()")
    public Object setAuthenticateAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object result = null;
        Object[] args = proceedingJoinPoint.getArgs(); // 被切面方法入参数
        String authInfoString = authService.getAuthInfo();
        AuthInfoModel authInfoModel = authService.hasAuth(authInfoString);

        if(authInfoModel!=null){
            log.info(proceedingJoinPoint.getSignature()+"aop鉴权成功! 入参:"+ Arrays.toString(args));
            if (args.length > 0 && args[0] instanceof AuthInfoModel) {
                AuthInfoModel authInfo = (AuthInfoModel)args[0];
                BeanUtils.copyProperties(authInfoModel,authInfo);
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String token = request.getHeader("Authorization");
                authInfo.setToken(token);
                log.info(authInfo.getToken());
            }
            result = proceedingJoinPoint.proceed();
        }else {
            log.info(proceedingJoinPoint.getSignature()+"aop鉴权失败! 入参:"+Arrays.toString(args)+"authInfoString:"+authInfoString);
            return JSONObject.parseObject(authInfoString,JsonResult.class);
        }
        return result;
    }

}
