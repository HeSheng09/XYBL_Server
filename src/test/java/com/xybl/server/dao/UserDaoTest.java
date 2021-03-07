package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static java.sql.DriverManager.println;

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
//        String enPwd=userDao.getUserById("1612715026000001").getPwd();
//        System.out.println(MD5Util.validText("password0", enPwd));
        System.out.println(userDao.getUserById("1612717415512000"));
    }

    @Test
    void getLastUserId() {
        System.out.println(userDao.getLastUserId());
    }

    @Test
    void addOneStudent(){
        User user=new User("111","111", "password",false);
        Student student=new Student(user);
        student.setStu_name("小明");
        student.setAddress("武汉大学");
        student.setEmail("xiaoming@xxx.com.cn");
        student.setTel("12345678901");
        System.out.println(student);
        userDao.addOneUser(user);
        userDao.addOneStu(student);
    }

    @Test
    void testLogin(){
        User test = userDao.getUserByName("测试学生1");
        System.out.println(test.toString());

        User a = userDao.getUserById("1612715026000001");
        System.out.println(a.toString());
    }

    @Test
    void testGetLastNsName(){
        String a = userDao.getLastNsNameNum("00000000000");
        System.out.println(a);
    }

    @Test
    void getDmschName(){
        String name = userDao.getDmschName("0000000000000");
        System.out.println(name);
    }

    @Test
    void testGetNsuserById(){
        NsUser nsUser = userDao.getNsUserById("1615086940718005");
        System.out.println(nsUser);
    }
}