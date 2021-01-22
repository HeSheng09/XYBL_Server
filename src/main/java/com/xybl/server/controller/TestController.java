package com.xybl.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {


    /**
    * @Description: test
    * @Param: []
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2021/1/22
    */
    @GetMapping("/test")
    public Map<String,Object> test(){
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("msg","ok");
        return res;
    }
}
