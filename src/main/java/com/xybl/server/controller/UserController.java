package com.xybl.server.controller;

import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.MD5Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
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
        String enPwd = MD5Util.getEncryptedText(pwd);//密码加密
        Student stu = new Student(id, name, enPwd);
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

    @GetMapping("/authorize")
    Map<String, Object> authorize(@RequestParam(name = "ns_name")String ns_name,
                                  @RequestParam(name = "level")String level,
                                  @RequestParam(name = "describe")String describe,
                                  @RequestParam(name = "autho_name")String autho_name,
                                  @RequestParam(name = "tel", defaultValue = "null")String tel,
                                  @RequestParam(name = "email", defaultValue = "null")String email){
        //0.返回消息
        String msg = "";
        //1.生成id
        String id = userService.genId();
        //2.生成pwd
        String pwd = String.valueOf((int)Math.ceil((Math.random()*9+1)*100000));
        String enPwd = MD5Util.getEncryptedText(pwd); //密码加密用于存入数据库
        //3.生成userName
        String userName = userService.genNsUserName(autho_name, describe, level);
        if(userName == ""){
            msg = "no right to authorize";
            logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
            return response(401, msg);
        }
        //4.封装成NsUser及存入t_nsuser表
        NsUser nsUser = new NsUser(id, userName);
        nsUser.setPwd(enPwd);
        nsUser.setNs_name(ns_name);
        nsUser.setEmail(email);
        nsUser.setTel(tel);
        userService.addOneNsu(nsUser);
        msg = "ok";
        //5.添加日志
        logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
        logService.addOneLog(nsUser.getId(), "register", msg);
        //6.返回前端
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", msg);
        res.put("name", userName);
        res.put("pwd", pwd);
        return res;
    }

}
