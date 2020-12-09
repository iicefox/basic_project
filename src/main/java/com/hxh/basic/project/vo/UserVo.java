package com.hxh.basic.project.vo;

import java.time.LocalDateTime;

/**
 * @author huangxunhui
 * Date: Created in 2020/3/6 4:13 下午
 * Utils: Intellij Idea
 * Description: 用户视图模型
 */
public class UserVo {

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

    public UserVo() {
    }

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

    public String toString() {
        return "UserVo(id=" + this.getId() + ", nickname=" + this.getNickname() + ", username=" + this.getUsername() + ", birthday=" + this.getBirthday() + ")";
    }
}
