package com.hxh.basic.project.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @author huangxunhui
 * Date: Created in 2019-05-22 11:52
 * Utils: Intellij Idea
 * Description: 分页需要的表单数据
 */
@Data
public class PageForm<T extends PageForm<?>>{

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
     * @return 返回 pageForm
     */
    public T calcCurrent(){
        current = (current - 1 ) * size;
        return (T) this;
    }
}

