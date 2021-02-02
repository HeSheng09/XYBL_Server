package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDaoTest
 * <p></p>
 * @author hesheng
 * @create 2021/2/3
 **/
@SpringBootTest(classes = ServerApplication.class)
class UserDaoTest {
    @Resource
    private UserDao userDao;

    @Test
    void addOneUser() {
        User user=new User();
        user.setId("920210202000002");
        user.setName("测试学生2");
        user.setPwd("password");
        user.setRole(9);
        user.setEmail("teststudent02@qq.com");
        user.setTel("12345678901");
        userDao.addOneUser(user);
    }

    @Test
    void getUserById() {
        System.out.println(userDao.getUserById("920210202000002"));
    }
}