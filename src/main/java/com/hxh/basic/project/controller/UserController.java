package com.hxh.basic.project.controller;


import com.hxh.basic.project.annotation.ResponseResult;
import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.form.user.AddUserForm;
import com.hxh.basic.project.form.user.ListUserForm;
import com.hxh.basic.project.service.UserService;
import com.hxh.basic.project.util.ResultVOUtil;
import com.hxh.basic.project.vo.PageVO;
import com.hxh.basic.project.vo.ResultVO;
import com.hxh.basic.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户前端控制器
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 13:21
 * <p>
 * Copyright 2021 yomu Inc.
 */
@RestController
@RequestMapping("/user")
@ResponseResult
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param userForm 表单数据
     * @return 成功或者失败
     */
    @PostMapping("/addUser")
    public ResultVO addUser(@Validated @RequestBody AddUserForm userForm) {
        if (userService.addUser(userForm)) {
            return ResultVOUtil.success();
        } else {
            return ResultVOUtil.error(ResultEnum.ADD_ERROR);
        }
    }

    /**
     * 获取用户列表
     *
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    @GetMapping("/listUser")
    public PageVO<UserVO> listUser(@Validated ListUserForm listUserForm) {
        return userService.listUser(listUserForm);
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

}
