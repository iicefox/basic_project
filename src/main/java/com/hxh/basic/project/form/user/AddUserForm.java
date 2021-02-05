package com.hxh.basic.project.form.user;

import com.hxh.basic.project.entity.User;
import com.hxh.basic.project.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 添加用户需要的表单数据
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
@ToString
@Getter
@Setter
public class AddUserForm implements BaseForm<User> {

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为为空")
    @Length(min = 1, max = 10, message = "昵称长度限制为1~10")
    private String nickname;

    /**
     * 生日
     */
    @Past(message = "生日时间必须小于当前时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 1, max = 16, message = "用户名长度限制为1~16")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,20}$", message = "密码不符合规范")
    private String password;

    /**
     * 构造实体
     *
     * @return 实体对象
     */
    @Override
    public User buildEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}
