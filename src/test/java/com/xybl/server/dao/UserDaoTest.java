package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.User;
import com.xybl.server.utils.UserUtil;
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
        user.setId(UserUtil.genUserId("普通用户","湖北省",4));
        user.setName("测试学生5");
        user.setPwd("password");
        user.setRole(6);
        user.setEmail("teststudent02@qq.com");
        user.setTel("12345678901");
        userDao.addOneUser(user);
    }

    @Test
    void getUserById() {
        System.out.println(userDao.getUserById("920210202000002"));
    }

    @Test
    void getLastUserId() {
        System.out.println(userDao.getLastUserId());
    }
}