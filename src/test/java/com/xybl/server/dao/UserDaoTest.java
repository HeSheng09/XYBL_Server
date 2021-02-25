package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.User;
import com.xybl.server.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
        User user=new User("1612717415512000","测试学生0",true);
        user.setPwd(MD5Util.getEncryptedText("password"));
        userDao.addOneUser(user);
    }

    @Test
    void getUserById() {
        String enPwd=userDao.getUserById("1612715026000001").getPwd();
        System.out.println(MD5Util.validText("password0", enPwd));
    }

    @Test
    void getLastUserId() {
        System.out.println(userDao.getLastUserId());
    }
}