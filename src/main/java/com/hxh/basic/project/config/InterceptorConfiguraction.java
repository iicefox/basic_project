package com.hxh.basic.project.config;

import com.hxh.basic.project.interceptor.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将ResponseResultInterceptor拦截器添加到SpringBoot的配置中
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 13:21
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@Configuration
public class InterceptorConfiguraction implements WebMvcConfigurer {
    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor此方法添加拦截器
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**");
    }
}
