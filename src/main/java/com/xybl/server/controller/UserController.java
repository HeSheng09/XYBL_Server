package com.xybl.server.controller;

import com.xybl.server.entity.Student;
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
     * <p>根据用户名及密码登录，成功登录会返回role</p>
     * @param name java.lang.String.
     * @param pwd java.lang.String.
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author liubocai
     * @create: 2021-03-05
     */
    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam(name = "name")String name,
                                     @RequestParam(name = "pwd")String pwd){
        return userService.login(name, pwd);
    }

    /**
    * register
    * <p>学生注册</p>
    * @param name java.lang.String.
     * @param pwd java.lang.String.
     * @param email java.lang.String.
     * @param tel java.lang.String.
     * @param address java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author liubocai
    * @create: 2021-03-05
    */
    @GetMapping("/stu_register")
    public Map<String, Object> register(@RequestParam(name = "name")String name,
                                        @RequestParam(name = "pwd")String pwd,
                                        @RequestParam(name = "stu_name")String stu_name,
                                        @RequestParam(name = "email", defaultValue = "null")String email,
                                        @RequestParam(name = "tel", defaultValue = "null")String tel,
                                        @RequestParam(name = "address", defaultValue = "null")String address){
        //1.生成id，以当前时间戳
        String id= userService.genId();
        //2.封装成user
        Student stu = new Student(id, name, pwd);
        stu.setEmail(email);
        stu.setTel(tel);
        stu.setAddress(address);
        stu.setStu_name(stu_name);
        //3.入库
        int isAdd = userService.addOneStu(stu);
        //4.返回消息
        String msg;
        switch (isAdd){
            case 200: msg = "ok"; break;
            case 401: msg = "user name has existed"; break;
            default: msg = "";
        }
        //5.添加操作日志
        logService.addOneLog(stu.getId(), "register", msg);
        return response(isAdd, msg);

    }

}
