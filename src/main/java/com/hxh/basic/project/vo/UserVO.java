package com.hxh.basic.project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@Getter
@ToString
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

}
