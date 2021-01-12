package com.hxh.basic.project.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页需要的表单数据
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@Setter
@Getter
@ToString
public class PageForm<T extends PageForm<?>> {

    /**
     * 页码
     */
    @Min(value = 1, message = "页码输入有误")
    @NotNull(message = "页码不能为空")
    private Integer current;

    /**
     * 每页显示的数量
     */
    @Range(min = 1, max = 100, message = "每页显示的数量输入有误")
    @NotNull(message = "每页显示的数量不能为空")
    private Integer size;

    /**
     * 计算当前页 ,方便mysql 进行分页查询
     * @return 返回 pageForm
     */
    public T calcCurrent(){
        current = (current - 1 ) * size;
        return (T) this;
    }

}

