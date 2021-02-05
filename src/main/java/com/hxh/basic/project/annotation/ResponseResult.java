package com.hxh.basic.project.annotation;


import java.lang.annotation.*;

/**
 * 包装注解 用来标注方法返回值, 是否需要包装成ResultVO
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 11:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

}
