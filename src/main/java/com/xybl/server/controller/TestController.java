package com.xybl.server.controller;

import com.xybl.server.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    /**
    * @Description: test
    * @Param: [id]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2021/1/25
    */
    @GetMapping("/test")
    public Map<String,Object> test(@RequestParam(name = "id")int id){
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("msg","ok");
        Map<String,Object> data=new HashMap<>();
        data.put("id",id);
        data.put("info",testService.test(id).getInfo());
        res.put("data",data);
        return res;
    }
}
