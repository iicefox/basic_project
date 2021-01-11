package com.hxh.basic.project.service;

import com.hxh.basic.project.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.basic.project.form.user.AddUserForm;
import com.hxh.basic.project.form.user.ListUserForm;
import com.hxh.basic.project.vo.PageVO;
import com.hxh.basic.project.vo.UserVO;

/**
 * 用户服务类
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public interface IUserService extends IService<User> {

    /**
     * 添加用户
     *
     * @param userForm 表单数据
     * @return true 或者 false
     */
    boolean addUser(AddUserForm userForm);

    /**
     * 获取用户列表
     *
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    PageVO<UserVO> listUser(ListUserForm listUserForm);

    /**
     * 删除用户
     *
     * @param id id
     */
    void deleteUser(String id);

}
