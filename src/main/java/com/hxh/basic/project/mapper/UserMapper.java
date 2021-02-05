package com.hxh.basic.project.mapper;

import com.hxh.basic.project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxh.basic.project.form.user.ListUserForm;
import com.hxh.basic.project.vo.UserVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库资源操作类
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户列表
     *
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    @Select("SELECT id,nickname,username,birthday FROM `user` WHERE `status`= #{status} LIMIT #{current},#{size}")
    List<UserVO> listUser(ListUserForm listUserForm);

}
