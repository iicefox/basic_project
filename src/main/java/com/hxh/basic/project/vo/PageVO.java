package com.hxh.basic.project.vo;

import com.hxh.basic.project.form.PageForm;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 分页视图对象
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
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
     * @param pageForm 分页表单
     * @return 返回分页信息
     */
    public PageVO<T> setCurrentAndSize(PageForm<?> pageForm){
        BeanUtils.copyProperties(pageForm,this);
        return this;
    }

    /**
     * 设置总记录数
     * @param total 总记录数
     */
    public void setTotal(Integer total) {
        this.total = total;
        this.setPages(this.total % this.size > 0 ? this.total / this.size + 1 : this.total / this.size);
    }

    public List<T> getRecords() {
        return this.records;
    }

    public Integer getTotal() {
        return this.total;
    }

    public Integer getPages() {
        return this.pages;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageVo(records=" + this.getRecords() + ", total=" + this.getTotal() + ", pages=" + this.getPages() + ", current=" + this.getCurrent() + ", size=" + this.getSize() + ")";
    }
}
