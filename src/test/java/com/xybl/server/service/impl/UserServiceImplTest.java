package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.NsUser;
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

    @Test
    void testLogin(){

    }

    @Test
    void testAutho(){
        String id = userService.genId();
        String pwd = String.valueOf((int)Math.ceil((Math.random()*9+1)*100000));
        System.out.println(pwd);
        String enPwd = MD5Util.getEncryptedText(pwd);
        String name = userService.genNsUserName("0000000000001", "湖北省", "0");
        NsUser nsUser = new NsUser(id, name);
        nsUser.setPwd(enPwd);
        nsUser.setNs_name("省级账号管理员");
        System.out.println(nsUser.toString());
        userService.addOneNsu(nsUser);
    }
}