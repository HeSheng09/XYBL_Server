package com.xybl.server.controller;

import com.xybl.server.entity.Test;
import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.service.TestService;
import com.xybl.server.utils.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * TestController
 * <p>测试项目框架能否正常运行。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    private LogService logService;

    /**
     * index
     * <p>测试</p>
     *
     * @param id int.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/2/2
     */
    @GetMapping("/index")
    public Map<String, Object> index(@RequestParam(name = "id") int id) {

        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        // expression
        data.put("info", testService.test(id).getInfo());
//        logService.addOneLog(new User("020210203202112170001","系统管理员",true),"插入日志测试。","成功");

        return response(200, "ok", data);
    }

    @GetMapping("/test")
    public Test test(@RequestParam(name = "param") String param) {
        System.out.println(param);
        Test test = new Test();
        test.setId(1);
        test.setInfo("测试");
        return test;
    }

}
