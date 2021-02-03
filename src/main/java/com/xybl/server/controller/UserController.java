package com.xybl.server.controller;

import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

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

    /**
    * login
    * <p>登录</p>
    * @param id java.lang.String.
     * @param pwd java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author liubocai
    * @create: 2021-02-03
    */
    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam(name = "id")String id,
                                     @RequestParam(name = "pwd")String pwd){
        Map<String, Object> res = new HashMap<>();
        res.put("info", userService.login(id, pwd));
        return res;
    }

    /**
    * register
    * <p>注册</p>
    * @param name java.lang.String.
     * @param pwd java.lang.String.
     * @param roleType int.
     * @param email java.lang.String.
     * @param tel java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author liubocai
    * @create: 2021-02-03
    */
    @GetMapping("/register")
    public Map<String, Object> register(@RequestParam(name = "name")String name,
                                        @RequestParam(name = "pwd")String pwd,
                                        @RequestParam(name = "role")int roleType,
                                        @RequestParam(name = "email", defaultValue = "noemail")String email,
                                        @RequestParam(name = "tel", defaultValue = "notel")String tel){
        //1.生成id，以当前时间戳
        String id = String.valueOf(System.currentTimeMillis());
        //2.封装成user
        User user = new User(id, name, roleType);
        user.setEmail(email);
        user.setPwd(pwd);
        user.setTel(tel);
        //3.入库
        boolean isAdd = userService.addOneUser(user);
        //4.返回消息
        Map<String, Object> res = new HashMap<>();
        res.put("info", isAdd);
        res.put("user", user);
        return res;
    }

}
