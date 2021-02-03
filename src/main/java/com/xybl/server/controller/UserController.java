package com.xybl.server.controller;

import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 * <p></p>
 * @author liubocai
 * @create 2021/2/3
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam(name = "id")String id,
                                     @RequestParam(name = "pwd")String pwd){
        Map<String, Object> res = new HashMap<>();
        res.put("info", userService.login(id, pwd));
        return res;
    }

}
