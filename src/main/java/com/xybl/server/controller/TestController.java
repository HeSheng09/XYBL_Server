package com.xybl.server.controller;

import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    * @param id int.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/2/2
    */
    @GetMapping("/index")
    public Map<String,Object> index(@RequestParam(name = "id")int id){

        Map<String,Object> data=new HashMap<>();
        data.put("id",id);
        // expression
        data.put("info",testService.test(id).getInfo());
        logService.addOneLog(new User("020210203202112170001","系统管理员",0),"插入日志测试。","成功");

        return response(200,"ok",data);
    }
}
