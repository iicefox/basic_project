package com.hxh.basic.project.exception;


import com.hxh.basic.project.enums.ResultEnum;

/**
 * 自定义异常
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 11:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8675330359281529640L;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 方法名称
     */
    private final String method;


    /**
     * 自定义异常
     *
     * @param resultEnum 返回枚举对象
     * @param method     方法
     */
    public CustomRuntimeException(ResultEnum resultEnum, String method) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     * @param method  方法
     */
    public CustomRuntimeException(Integer code, String message, String method) {
        super(message);
        this.code = code;
        this.method = method;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMethod() {
        return this.method;
    }

    @Override
    public String toString() {
        return "CustomRuntimeException(code=" + this.getCode() + ", method=" + this.getMethod() + ", message=" + super.getLocalizedMessage() + ")";
    }
}
