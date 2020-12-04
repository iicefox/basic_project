package com.hxh.basic.project.vo;

import lombok.Data;

/**
 * @author huangxunhui
 * Date: Created in 2018-12-17 11:05
 * Utils: Intellij Idea
 * Description: 固定返回格式
 */
@Data
public class ResultVo {

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
