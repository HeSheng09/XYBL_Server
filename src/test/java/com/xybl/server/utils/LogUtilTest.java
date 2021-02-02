package com.xybl.server.utils;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Log;
import com.xybl.server.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class LogUtilTest {

    @Test
    void genLogDetail() {
        User user=new User();
        user.setId("920210202000001");
        user.setName("测试学生");
        user.setRole(9);
        System.out.println(LogUtil.genLogDetail(user,"生成log文本测试","succeed", DatetimeUtil.getAndFormatDatetime()));
    }

    @Test
    void genLog() {
        User user=new User();
        user.setId("920210202000001");
        user.setName("测试学生");
        user.setRole(9);
        Log log=LogUtil.genLog(user,"生成log测试","succeed");
        System.out.println(log);
        System.out.println(log.getId().length());
    }
}