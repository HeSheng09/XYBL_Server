package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserServiceImplTest
 * <p></p>
 *
 * @author hesheng
 * @create 2021/2/3
 **/
@SpringBootTest(classes = ServerApplication.class)
class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    void testAddOneStudent() {
        Student student = new Student(new User(userService.genId(), "", MD5Util.getEncryptedText("password"), false));
        System.out.println(student);
        userService.addOneStu(student);
    }

    @Test
    void genId() {
        System.out.println(userService.genId());
    }
}