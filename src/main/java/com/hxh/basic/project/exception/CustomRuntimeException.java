package com.hxh.basic.project.exception;


import com.hxh.basic.project.enums.ResultEnum;

/**
 * @author huangxunhui
 * Date: Created in 2018-12-18 10:25
 * Utils: Intellij Idea
 * Description: 自定义异常
 */
public class CustomRuntimeException extends RuntimeException {

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
        super(resultEnum.getMsg());
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

    public String toString() {
        return "CustomRuntimeException(code=" + this.getCode() + ", method=" + this.getMethod() + ")";
    }

}