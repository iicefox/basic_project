package com.hxh.basic.project.controller;


import com.hxh.basic.project.annotation.ResponseResult;
import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.form.user.AddUserForm;
import com.hxh.basic.project.form.user.ListUserForm;
import com.hxh.basic.project.service.IUserService;
import com.hxh.basic.project.util.ResultVOUtil;
import com.hxh.basic.project.vo.PageVO;
import com.hxh.basic.project.vo.ResultVO;
import com.hxh.basic.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangxunhui
 * @date Created in 2020/3/6 4:30 下午
 * Utils: Intellij Idea
 * Description: 用户前端控制器
 */
@RestController
@RequestMapping("/user")
@ResponseResult
public class UserController {

    @Autowired
    private IUserService userService;

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
//    @GetMapping("/listUser")
//    public ResultVO listUser(@Validated ListUserForm listUserForm) {
//        return ResultVOUtil.success(userService.listUser(listUserForm));
//    }
    @GetMapping("/listUser")
    public PageVO<UserVO> listUser(@Validated ListUserForm listUserForm) {
        return userService.listUser(listUserForm);
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 成功或者失败
     */
//    @DeleteMapping("/deleteUser/{id}")
//    public ResultVO deleteUser(@PathVariable("id") String id) {
//        userService.deleteUser(id);
//        return ResultVOUtil.success();
//    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        System.out.println(1/0);
        userService.deleteUser(id);
    }
}
