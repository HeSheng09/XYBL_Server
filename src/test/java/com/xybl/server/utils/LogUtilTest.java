package com.xybl.server.utils;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Log;
import com.xybl.server.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LogUtilTest
 * <p></p>
 * @author hesheng
 * @create 2021/2/3
 **/
class LogUtilTest {

    @Test
    void genLogDetail() {
        User user=new User();
        user.setId("920210202000001");
        user.setName("测试学生");
//        user.setRole(9);
        System.out.println(LogUtil.genLogDetail(user,"生成log文本测试","succeed", DatetimeUtil.getAndFormatDatetime()));
    }

    @Test
    void genLog() {
        User user=new User();
        user.setId("920210202123456000001");
        user.setName("测试学生");
//        user.setRole(9);
        Log log=LogUtil.genLog(user,"生成log测试","succeed");
        System.out.println(log);
        System.out.println(log.getId().length());
    }

    @Test
    void whatever(){
        List<String> roles = Arrays.asList("系统管理员", "教育部", "省级", "市级", "县级", "学校", "普通用户", "其他用户", "other");
        System.out.println(roles.indexOf("what"));
    }
}