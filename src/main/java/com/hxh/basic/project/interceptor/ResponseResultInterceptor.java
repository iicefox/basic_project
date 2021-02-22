package com.hxh.basic.project.interceptor;


import com.hxh.basic.project.annotation.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截请求,解析@ResponseResult注解,是否此请求返回的值需要包装
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    /**
     * 标记名称
     */
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            // 不是请求的方法
            return false;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Class<?> clazz = handlerMethod.getBeanType();
        final Method method = handlerMethod.getMethod();

        if (clazz.isAnnotationPresent(ResponseResult.class)) {
            // 在类对象上加了注解
            request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
        }
        if (method.isAnnotationPresent(ResponseResult.class)) {
            // 在类对象上加了注解
            request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
        }
        return true;
    }
}

