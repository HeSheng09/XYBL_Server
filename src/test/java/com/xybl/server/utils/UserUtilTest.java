package com.xybl.server.utils;

import com.xybl.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserUtilTest
 * <p></p>
 * @author hesheng
 * @create 2021/2/3
 **/
class UserUtilTest {

    @Test
    void genUserId() {
        String userid=UserUtil.genUserId("系统管理员","上海市",1);
        System.out.println(userid);
        System.out.println(userid.length());
    }

    @Test
    void getLast_id() {

    }
}