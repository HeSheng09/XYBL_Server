package com.xybl.server.controller;

import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

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
    @Resource
    public LogService logService;

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
        int isLogin  = userService.login(id, pwd);
        String msg;
        switch (isLogin){
            case 200: msg = "ok";break;
            case 401: msg = "password error";break;
            case 402: {msg = "user doesn't exist"; return response(isLogin, msg);}
            default: msg="";
        }
        logService.addOneLog(userService.getUserById(id), "login", msg);
        return response(isLogin, msg);
    }

    /**
    * register
    * <p>注册</p>
    * @param name java.lang.String.
     * @param pwd java.lang.String.
     * @param roleType int.
     * @param email java.lang.String.
     * @param tel java.lang.String.
     * @param zone java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author liubocai
    * @create: 2021-02-03
    */
    @GetMapping("/register")
    public Map<String, Object> register(@RequestParam(name = "name")String name,
                                        @RequestParam(name = "pwd")String pwd,
                                        @RequestParam(name = "role")int roleType,
                                        @RequestParam(name = "email", defaultValue = "noemail")String email,
                                        @RequestParam(name = "tel", defaultValue = "notel")String tel,
                                        @RequestParam(name = "zone", defaultValue = "unknown")String zone){
        //1.生成id，以当前时间戳
//        String id = String.valueOf(System.currentTimeMillis());
        String id= userService.genId();
        //2.封装成user
        User user = new User(id, name, roleType==6);
//        user.setEmail(email);
        user.setPwd(pwd);
//        user.setTel(tel);
        //3.入库
        int isAdd = userService.addOneUser(user);
        //4.返回消息
        String msg;
        switch (isAdd){
            case 200: msg = "ok"; break;
            case 401: msg = "user name has existed"; break;
            default: msg = "";
        }
        //5.添加操作日志
        logService.addOneLog(user, "register", msg);
        return response(isAdd, msg);

    }

}
