package com.hxh.basic.project.vo;

import java.time.LocalDateTime;

/**
 * 用户视图模型
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
public class UserVO {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    public Integer getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getUsername() {
        return this.username;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserVo(id=" + this.getId() + ", nickname=" + this.getNickname() + ", username=" + this.getUsername() + ", birthday=" + this.getBirthday() + ")";
    }
}
