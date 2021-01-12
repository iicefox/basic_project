package com.hxh.basic.project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 固定返回格式
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@Getter
@Setter
@ToString
public class ResultVO {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体的内容
     */
    private Object data;

}
