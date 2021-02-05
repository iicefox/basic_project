package com.hxh.basic.project.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.basic.project.entity.User;
import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.exception.CustomRuntimeException;
import com.hxh.basic.project.form.user.AddUserForm;
import com.hxh.basic.project.form.user.ListUserForm;
import com.hxh.basic.project.mapper.UserMapper;
import com.hxh.basic.project.service.UserService;
import com.hxh.basic.project.util.MethodUtil;
import com.hxh.basic.project.vo.PageVO;
import com.hxh.basic.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户服务实现类
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     *
     * @param userForm 表单数据
     * @return true 或者 false
     * @updaters aaa
     */
    @Override
    public boolean addUser(AddUserForm userForm) {
        return save(userForm.buildEntity());
    }

    /**
     * 获取用户列表
     *
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    @Override
    public PageVO<UserVO> listUser(ListUserForm listUserForm) {
        PageVO<UserVO> pageVo = new PageVO<UserVO>().setCurrentAndSize(listUserForm);
        pageVo.setTotal(countUser(listUserForm.getStatus()));
        pageVo.setRecords(CollUtil.<UserVO>emptyIfNull(userMapper.listUser(listUserForm.calcCurrent())));
        return pageVo;
    }

    /**
     * 删除用户
     *
     * @param id id
     */
    @Override
    public void deleteUser(String id) {
        // 如果删除失败抛出异常。 -- 演示而已不推荐这样干
        if (!removeById(id)) {
            throw new CustomRuntimeException(ResultEnum.DELETE_ERROR, MethodUtil.getLineInfo());
        }
    }

    /**
     * 获取用户数量
     *
     * @param status 状态
     * @return 用户数量
     */
    private Integer countUser(String status) {
        return count(new QueryWrapper<User>().eq("status", status));
    }

}
