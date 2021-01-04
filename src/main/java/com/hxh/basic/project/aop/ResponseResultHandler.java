package com.hxh.basic.project.aop;

import com.hxh.basic.project.annotation.ResponseResult;
import com.hxh.basic.project.interceptor.ResponseResultInterceptor;
import com.hxh.basic.project.util.ResultVOUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 重写返回体
 *
 * <p>判断是否需要返回值包装，如果需要就直接包装.
 * 如果方法体报异常怎么办？处理异常也比较简单，只要判断body是否为异常类。
 * </p>
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@RestControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否请求 包含了 包装注解 标记, 没有就直接返回, 不需要重写返回体
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @SuppressWarnings("java:S2259")
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求 是否有包装标记
        ResponseResult responseResultAnno = (ResponseResult) request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT_ANN);
        return responseResultAnno != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ResultVOUtil.success(o);
    }
}
