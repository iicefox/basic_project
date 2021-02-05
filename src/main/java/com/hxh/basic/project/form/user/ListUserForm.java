package com.hxh.basic.project.form.user;

import com.hxh.basic.project.form.PageForm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * 获取用户列表需要的表单数据
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Getter
@Setter
public class ListUserForm extends PageForm<ListUserForm> {

    /**
     * 用户状态
     */
    @NotEmpty(message = "用户状态不能为空")
    @Range(min = -1, max = 1, message = "用户状态有误")
    private String status;

}
