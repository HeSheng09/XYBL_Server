package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserServiceImplTest
 * <p></p>
 * @author hesheng
 * @create 2021/2/3
 **/
@SpringBootTest(classes = ServerApplication.class)
class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Test
    void getLast_id() {
        System.out.println(userService.getLast_id());
    }
}