package com.xybl.server.controller;

import com.xybl.server.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

@RestController
@RequestMapping("/test")
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
    @GetMapping("/dbtest")
    public Map<String,Object> test(@RequestParam(name = "id")int id){

        Map<String,Object> data=new HashMap<>();
        data.put("id",id);
        // expression
        data.put("info",testService.test(id).getInfo());

        return response(200,"ok",data);
    }
}
