package com.hxh.basic.project.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态枚举类
 *
 * <p>
 *  <ul>
 *      <li>2000～2999 响应成功</li>
 *      <li>3000～3999 用户错误、权限错误等</li>
 *      <li>4000～4999 参数错误</li>
 *      <li>5000～4999 内部异常</li>
 *  </ul>
 * </p>
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 11:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {
    /**
     * 参数无效
     */
    PARAM_IS_INVALID(1001, "参数无效"),
    /**
     * 参数为空
     */
    PARAM_IS_BLANK(1002, "参数为空"),
    /**
     * 参数类型错误
     */
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    /**
     * 参数缺失
     */
    PARAM_NOT_COMPLETE(1004, "参数缺失"),


    /**
     * 用户未登录,访问的路径需要验证,请登录
     */
    USER_NOT_LOGGED_IN(2001, "用户未登录,访问的路径需要验证,请登录"),
    /**
     * 账号不存在或密码错误
     */
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    /**
     * 账号已被禁用
     */
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(2004, "用户不存在"),
    /**
     * 用户已存在
     */
    USER_HAS_EXISTED(2005, "用户已存在"),

    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(5000, "未知异常"),

    /**
     * 超时
     */
    TIME_OUT(5001, "超时"),

    /**
     * 添加失败
     */
    ADD_ERROR(5003, "添加失败"),

    /**
     * 更新失败
     */
    UPDATE_ERROR(5004, "更新失败"),

    /**
     * 删除失败
     */
    DELETE_ERROR(5005, "删除失败"),

    /**
     * 查找失败
     */
    GET_ERROR(5006, "查找失败"),

    /**
     * 格式错误
     */
    FORMAT_ERROR(4000, "参数格式错误"),

    /**
     * 参数类型不匹配
     */
    ARGUMENT_TYPE_MISMATCH(4004, "参数类型不匹配"),

    /**
     * 请求方式不支持
     */
    REQ_METHOD_NOT_SUPPORT(4001, "请求方式不支持"),

    /**
     * 响应成功
     */
    OK(2000, "ok"),
    ;

    private Integer code;

    private String message;

    /**
     * 通过状态码获取枚举对象
     *
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }

}

