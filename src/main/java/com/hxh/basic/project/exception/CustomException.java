package com.hxh.basic.project.exception;


import com.hxh.basic.project.enums.ResultEnum;

/**
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 11:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 6464111054678642386L;
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
    public CustomException(ResultEnum resultEnum, String method) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     * @param method  方法
     */
    public CustomException(Integer code, String message, String method) {
        super(message);
        this.code = code;
        this.method = method;
    }

    /**
     * @param message 错误信息
     * @param method  方法
     */
    public CustomException(String message, String method) {
        super(message);
        this.code = null;
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
        return "CustomException(code=" + this.getCode() + ", method=" + this.getMethod()+ ", message=" + super.getLocalizedMessage() + ")";
    }

}
