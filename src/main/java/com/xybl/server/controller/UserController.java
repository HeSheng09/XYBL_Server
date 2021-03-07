package com.xybl.server.controller;

import com.xybl.server.entity.*;
import com.xybl.server.service.DepartService;
import com.xybl.server.service.LogService;
import com.xybl.server.service.SchoolService;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.MD5Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.invoke.SwitchPoint;
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
    @Resource
    public SchoolService schoolService;
    @Resource
    public DepartService departService;

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
    Map<String, Object> authorize(@RequestParam(name = "authoId")String authoId,
                                  @RequestParam(name = "beNamed")String beNamed,
                                  @RequestParam(name = "tel", defaultValue = "null")String tel,
                                  @RequestParam(name = "email", defaultValue = "null")String email,
                                  @RequestParam(name = "web", defaultValue = "null")String web,
                                  @RequestParam(name = "address", defaultValue = "null")String address,
                                  @RequestParam(name = "postcode", defaultValue = "null")String postcode){
        //0.返回消息
        String msg = "";
        //0.获取授权者信息
        User author = userService.getUserById(authoId);
        System.out.println(author);
        String autho_name = author.getName();
        Department depart = departService.getDepartById(autho_name);
        if(depart == null){
            msg = "there is no such department";
            logService.addOneLog(authoId, "authorize", msg);
            return response(400, msg);
        }
        String level = departService.getDepartById(autho_name).getLevel();
        String nextL; //其将要授权账号的level
        switch (level){
            case "B":nextL = "T"; break;
            case "T":nextL = "J"; break;
            case "J":nextL = "X"; break;
            default: nextL = "S";
        }
        //1.生成id
        String id = userService.genId();
        //2.生成pwd
        String pwd = String.valueOf((int)Math.ceil((Math.random()*9+1)*100000));
        String enPwd = MD5Util.getEncryptedText(pwd); //密码加密用于存入数据库
        //3.生成userName
        String userName = userService.genNsUserName(autho_name, beNamed);
        if("".equals(userName)){
            msg = "no right to authorize";
            logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
            return response(401, msg);
        }
        //4.判断该userName是否已经在r_dmsch_name库中了
        if(userService.getDmschName(userName) != null){
            msg = "this department or school has been existed";
            logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
            return response(402, msg);
        }

        //4.封装成NsUser及存入t_nsuser,r_dmsch_name,t_school or t_department表
        NsUser nsUser = new NsUser(id, userName);
        nsUser.setPwd(enPwd);
        nsUser.setNs_name(beNamed);
        nsUser.setEmail(email);
        nsUser.setTel(tel);
        nsUser.setPrivilege(nextL+"0");
        userService.addOneNsu(nsUser);//入Nsuser和user库  //如nsuser库时名字变成了乱码，
        userService.addOneDmschName(beNamed, userName);//入r_dmsch_name库
        if( !"000".equals(autho_name.substring(4,7))){ //判断授权者为县级机构，则入t_school库
            School school = new School(userName, beNamed);
            school.setWeb(web);
            school.setPostcode(postcode);
            school.setTel(tel);
            school.setAddress(address);
            schoolService.addSchool(school);
        }else{//判断授权者为县级以上机构，则入t_department库
            Department department = new Department(userName, beNamed);
            department.setAddress(address);
            department.setWeb(web);
            department.setLevel(nextL);
            departService.addDepartment(department);
        }
        msg = "ok";
        //5.添加日志
        logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
        //6.返回前端
        return response(200,msg,new User(id,userName,pwd,true));
    }

    @RequestMapping("/personal")
    Map<String, Object> personal(@RequestParam(name = "authoId")String authoId,
                                 @RequestParam(name = "privilege")String privilege,
                                 @RequestParam(name = "ns_name", defaultValue = "null")String ns_name,
                                 @RequestParam(name = "tel", defaultValue = "null")String tel,
                                 @RequestParam(name = "email", defaultValue = "null")String email){
        //0.初始化参数
        String msg = "";
        NsUser author = userService.getNsUserById(authoId);
        String authoName = author.getName();
        //1.生成user相关参数
        String id = userService.genId();
        String pwd = MD5Util.genSixPwd();
        String enPwd = MD5Util.getEncryptedText(pwd);
        String nameCode = userService.genNsUserPerName(authoName, privilege);
        boolean role = true;
        //2.检查:是否有权限，是否重复
        if(!"0".equals(authoName.substring(10,11))){
            msg = "authorizer has no right to authorize personal account";
            logService.addOneLog(authoId, "generate personal management accout", msg);
            return response(400, msg);
        }
        if(userService.getUserByName(nameCode) != null){
            msg = "user name has been exsited";
            logService.addOneLog(authoId, "generate personal management accout", msg);
            return response(401, msg);
        }
        //3.入库
        NsUser nsUser = new NsUser(id, nameCode);
        nsUser.setPwd(enPwd);
        nsUser.setRole(role);
        nsUser.setNs_name(ns_name);
        nsUser.setPrivilege(author.getPrivilege().charAt(0) + privilege);
        nsUser.setTel(tel);
        nsUser.setEmail(email);
        userService.addOneNsu(nsUser);
        //4.添加日志及返回消息
        msg = "ok";
        logService.addOneLog(authoId, "generate personal management accout", msg);
        return response(200, "ok", new User(id, nameCode, pwd, true));
    }

    @RequestMapping("/getuser")
    Map<String, Object> getUser(@RequestParam(name = "requestId")String requestId,
                                @RequestParam(name = "id")String id){
        User user = userService.getUserById(id);
//        if(user.getRole()){
//            Student ruser = userService.getStuById(id);
//        }else{
//            NsUser ruser = userService.getNsUserById(id);
//        }
        User ruser = new User();
        String msg="ok";
        logService.addOneLog(requestId, "get User("+id+") information" ,msg);
        return response(200, msg, ruser);
    };


}
