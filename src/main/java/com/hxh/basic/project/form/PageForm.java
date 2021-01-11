package com.hxh.basic.project.form;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * 分页需要的表单数据
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public class PageForm<T extends PageForm<?>> {

    /**
     * 页码
     */
    @Min(value = 1, message = "页码输入有误")
    private Integer current;

    /**
     * 每页显示的数量
     */
    @Range(min = 1, max = 100, message = "每页显示的数量输入有误")
    private Integer size;

    /**
     * 计算当前页 ,方便mysql 进行分页查询
     *
     * @return 返回 pageForm
     */
    public T calcCurrent() {
        current = (current - 1) * size;
        return (T) this;
    }

    public @Min(value = 1, message = "页码输入有误") Integer getCurrent() {
        return this.current;
    }

    public @Range(min = 1, max = 100, message = "每页显示的数量输入有误") Integer getSize() {
        return this.size;
    }

    public void setCurrent(@Min(value = 1, message = "页码输入有误") Integer current) {
        this.current = current;
    }

    public void setSize(@Range(min = 1, max = 100, message = "每页显示的数量输入有误") Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageForm(current=" + this.getCurrent() + ", size=" + this.getSize() + ")";
    }
}

