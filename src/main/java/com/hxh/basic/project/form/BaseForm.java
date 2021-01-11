package com.hxh.basic.project.form;

/**
 * 通用表单
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public interface BaseForm<T> {

    /**
     * 构建实例
     *
     * @return 返回实体类
     */
    public abstract T buildEntity();

}
