package com.hxh.basic.project.vo;

import com.hxh.basic.project.form.PageForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 分页视图对象
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Setter
@Getter
@ToString
public class PageVO<T> {
    /**
     * 分页数据
     */
    private List<T> records;
    /**
     * 总条数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 查询数量
     */
    private Integer size;

    /**
     * 设置当前页和每页显示的数量
     *
     * @param pageForm 分页表单
     * @return 返回分页信息
     */
    public PageVO<T> setCurrentAndSize(PageForm<?> pageForm) {
        BeanUtils.copyProperties(pageForm, this);
        return this;
    }

    /**
     * 设置总记录数
     *
     * @param total 总记录数
     */
    public void setTotal(Integer total) {
        this.total = total;
        this.setPages(this.total % this.size > 0 ? this.total / this.size + 1 : this.total / this.size);
    }

}
