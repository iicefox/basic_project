package com.hxh.basic.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yomu
 * @version 1.0
 * @date 2021/2/3 11:55
 * <p>
 * Copyright 2021 yomu Inc.
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void deleteUser() {
        userService.deleteUser("1");
    }
}