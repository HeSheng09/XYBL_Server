package com.xybl.server.controller;

import com.alibaba.fastjson.JSONObject;
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
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * UserController
 * <p></p>
 *
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
     *
     * @param name java.lang.String.
     * @param pwd  java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author liubocai
     * @create: 2021-03-05
     */
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "pwd") String pwd) {
        System.out.println(name);
        System.out.println(pwd);
        return userService.login(name, pwd);
    }

    /**
     * register
     * <p>学生注册</p>
     *
     * @param name    java.lang.String.
     * @param pwd     java.lang.String.
     * @param email   java.lang.String.
     * @param tel     java.lang.String.
     * @param address java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author liubocai
     * @create: 2021-03-05
     */
    @GetMapping("/stu_register")
    public Map<String, Object> register(@RequestParam(name = "name") String name,
                                        @RequestParam(name = "pwd") String pwd,
                                        @RequestParam(name = "stu_name") String stu_name,
                                        @RequestParam(name = "schoolId") String schId,
                                        @RequestParam(name = "email", defaultValue = "null") String email,
                                        @RequestParam(name = "tel", defaultValue = "null") String tel,
                                        @RequestParam(name = "address", defaultValue = "null") String address) {


        //1.生成id，以当前时间戳
        String id = userService.genId();
        //2.封装成user
        String enPwd = MD5Util.getEncryptedText(pwd);//密码加密
        Student stu = new Student(id, name, enPwd);
        stu.setEmail(email);
        stu.setTel(tel);
        stu.setAddress(address);
        stu.setStu_name(stu_name);
        //3.入库
        int isAdd = userService.addOneStu(stu);
        try {
            schoolService.addOneStu(schId, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.返回消息
        String msg;
        switch (isAdd) {
            case 200:
                msg = "ok";
                break;
            case 401:
                msg = "user name has existed";
                break;
            default:
                msg = "";
        }
        //5.添加操作日志
        logService.addOneLog(stu.getId(), "register", msg);
        return response(isAdd, msg);

    }

    @GetMapping("/authorize")
    Map<String, Object> authorize(@RequestParam(name = "authoId") String authoId,
                                  @RequestParam(name = "beNamed") String beNamed,
                                  @RequestParam(name = "tel", defaultValue = "null") String tel,
                                  @RequestParam(name = "email", defaultValue = "null") String email,
                                  @RequestParam(name = "web", defaultValue = "null") String web,
                                  @RequestParam(name = "address", defaultValue = "null") String address,
                                  @RequestParam(name = "postcode", defaultValue = "null") String postcode) {
        //

        //0.返回消息
        String msg = "";
        //0.获取授权者信息
        User author = userService.getUserById(authoId);
        System.out.println(author);
        String autho_name = author.getName();
        Department depart = departService.getDepartById(autho_name);
        if (depart == null) {
            msg = "there is no such department";
            logService.addOneLog(authoId, "authorize", msg);
            return response(400, msg);
        }
        String level = departService.getDepartById(autho_name).getLevel();
        String nextL; //其将要授权账号的level
        switch (level) {
            case "B":
                nextL = "T";
                break;
            case "T":
                nextL = "J";
                break;
            case "J":
                nextL = "X";
                break;
            default:
                nextL = "S";
        }
        //1.生成id
        String id = userService.genId();
        //2.生成pwd
        String pwd = String.valueOf((int) Math.ceil((Math.random() * 9 + 1) * 100000));
        String enPwd = MD5Util.getEncryptedText(pwd); //密码加密用于存入数据库
        //3.生成userName
        String userName = userService.genNsUserName(autho_name, beNamed);
        if ("".equals(userName)) {
            msg = "no right to authorize";
            logService.addOneLog(userService.getUserByName(autho_name).getId(), "authorize", msg);
            return response(401, msg);
        }
        //4.判断该userName是否已经在r_dmsch_name库中了
        if (userService.getDmschName(userName) != null) {
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
        nsUser.setPrivilege(nextL + "0");
        userService.addOneNsu(nsUser);//入Nsuser和user库  //如nsuser库时名字变成了乱码，
        userService.addOneDmschName(beNamed, userName);//入r_dmsch_name库
        if (!"000".equals(autho_name.substring(4, 7))) { //判断授权者为县级机构，则入t_school库
            School school = new School(userName, beNamed);
            school.setWeb(web);
            school.setPostcode(postcode);
            school.setTel(tel);
            school.setAddress(address);
            schoolService.addSchool(school);
            departService.addOneSch(autho_name, userName);
        } else {//判断授权者为县级以上机构，则入t_department库
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
        return response(200, msg, new User(id, userName, pwd, true));
    }

    @RequestMapping("/personal")
    Map<String, Object> personal(@RequestParam(name = "authoId") String authoId,
                                 @RequestParam(name = "privilege") String privilege,
                                 @RequestParam(name = "ns_name", defaultValue = "null") String ns_name,
                                 @RequestParam(name = "tel", defaultValue = "null") String tel,
                                 @RequestParam(name = "email", defaultValue = "null") String email) {
        //0.初始化参数
        String msg = "";
        User uAuthor = userService.getUserById(authoId);
        NsUser nsuAuthor = userService.getNsUserById(authoId);
        String authoName = uAuthor.getName();
        //1.生成user相关参数
        String id = userService.genId();
        String pwd = MD5Util.genSixPwd();
        String enPwd = MD5Util.getEncryptedText(pwd);
        String nameCode = userService.genNsUserPerName(authoName, privilege);
        boolean role = true;
        //2.检查:是否有权限，是否重复
        if (!"0".equals(authoName.substring(10, 11))) {
            msg = "authorizer has no right to authorize personal account";
            logService.addOneLog(authoId, "generate personal management accout", msg);
            return response(400, msg);
        }
        if (userService.getUserByName(nameCode) != null) {
            msg = "user name has been exsited";
            logService.addOneLog(authoId, "generate personal management accout", msg);
            return response(401, msg);
        }
        //3.入库
        NsUser nsUser = new NsUser(id, nameCode);
        nsUser.setPwd(enPwd);
        nsUser.setRole(role);
        nsUser.setNs_name(ns_name);
        nsUser.setPrivilege(nsuAuthor.getPrivilege().charAt(0) + privilege);
        nsUser.setTel(tel);
        nsUser.setEmail(email);
        userService.addOneNsu(nsUser);
        //入关系库
        departService.addOneNs(authoName, id);
        //4.添加日志及返回消息
        msg = "ok";
        logService.addOneLog(authoId, "generate personal management accout", msg);
        return response(200, "ok", new User(id, nameCode, pwd, true));
    }


    @RequestMapping("/user_info")
    Map<String, Object> getUserInfo(@RequestParam(name = "user_id") String user_id) {
        try {
            User user = userService.getUserById(user_id);
            if (user == null) {
                logService.addOneLog(user_id, "ask for user infomation", "succeed");
                return response(401, "there is no such user");
            } else {
                if (user.getRole()) {
                    //ns
                    NsUser nsUser = userService.getNsUserById(user_id);
                    logService.addOneLog(user_id, "ask for user infomation", "succeed");
                    return response(200, "ok", nsUser);
                } else {
                    Student student = userService.getStuById(user_id);
                    logService.addOneLog(user_id, "ask for user infomation", "succeed");
                    return response(200, "ok", student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for user infomation", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/getuserinfo")
    Map<String, Object> getUser(@RequestParam(name = "requestId") String requestId,
                                @RequestParam(name = "id") String id) {
        //1.回收参数
        User user = userService.getUserById(id);
        User requestUser = userService.getUserById(requestId);
        //2.判断权限
        if (user == null) {
            String msg = "there is no such user";
            logService.addOneLog(requestId, "get User(" + id + ") information", msg);
            return response(401, msg);
        }
        if (!id.equals(requestId) && requestUser.getRole()) {
            String msg = "has no right";
            logService.addOneLog(requestId, "get User(" + id + ") information", msg);
            return response(400, msg);
        }
        //3.查询信息
        User tempUser = new User();
        Object tempDmsch = new Object();
        String dmschUid = "";
        if (user.getRole()) {
            tempUser = userService.getNsUserById(id);
            String nid = userService.getDmschNidByNsUid(id);
            System.out.println(nid);
            if ("000".equals(nid.substring(7, 10))) {
                tempDmsch = departService.getDepartById(nid.substring(0, 10) + "000");
                dmschUid = userService.getUserByName(nid.substring(0, 10) + "000").getId();
            } else {
                tempDmsch = schoolService.getSchoolById(nid.substring(0, 10) + "000");
                dmschUid = userService.getUserByName(nid.substring(0, 10) + "000").getId();

            }
        } else {
            tempUser = userService.getStuById(id);
            String tempSchId = userService.getSchIdByStuid(id);
            dmschUid = userService.getUserByName(tempSchId).getId();
            tempDmsch = schoolService.getSchoolById(tempSchId);
        }

        //4.返回封装消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", tempUser);
        jsonObject.put("dmsch", tempDmsch);
        jsonObject.put("dmschUid", dmschUid);
        String msg = "ok";
        logService.addOneLog(requestId, "get User(" + id + ") information", msg);
        return response(200, msg, jsonObject);
    }

    ;


    @RequestMapping("/update")
    public Map<String, Object> updateUserInfo(@RequestParam(name = "id") String id,
                                              @RequestParam(name = "pwd", defaultValue = "not_provided") String pwd,
                                              @RequestParam(name = "name", defaultValue = "not_provided") String name,
                                              @RequestParam(name = "uName", defaultValue = "not_provided") String uName,
                                              @RequestParam(name = "tel", defaultValue = "not_provided") String tel,
                                              @RequestParam(name = "address", defaultValue = "not_provided") String address,
                                              @RequestParam(name = "email", defaultValue = "not_provided") String email) {//0.更新user表
        User user = userService.getUserById(id);
        if (!"not_provided".equals(name)) {
            if (userService.getUserByName(name) != null) {
                logService.addOneLog(id, "(id=" + id + ")try to update", "name has been used");
                return response(400, "name has been used");
            }
            user.setName(name);
        }
        if (!"not_provided".equals(pwd)) {
            String enPwd = MD5Util.getEncryptedText(pwd);
            user.setPwd(enPwd);
        }
        userService.updateUser(user);

        //1.判斷是学生用户修改
        if (!user.getRole()) {
            Student stu = new Student(id);
            if (!"not_provided".equals(uName)) {
                stu.setStu_name(uName);
            }
            if (!"not_provided".equals(tel)) {
                stu.setTel(tel);
            }
            if (!"not_provided".equals(address)) {
                stu.setAddress(address);
            }
            if (!"not_provided".equals(email)) {
                stu.setEmail(email);
            }
            try {
                userService.updateStu(stu);
                logService.addOneLog(id, "user(id=" + id + ") update self info", "succeed");
                return response(200, "ok");
            } catch (Exception e) {
                e.printStackTrace();
                logService.addOneLog(id, "user(id=" + id + ") update self info", "failed");
                return response(500, "server error");
            }
            //2.判斷管理员用户修改
        } else {
            NsUser nsUser = new NsUser(id);
            if (!"not_provided".equals(uName)) {
                nsUser.setNs_name(uName);
            }
            if (!"not_provided".equals(email)) {
                nsUser.setEmail(email);
            }
            if (!"not_provided".equals(tel)) {
                nsUser.setTel(tel);
            }
            try {
                userService.updateNs(nsUser);
                logService.addOneLog(id, "user(id=" + id + ") update self info", "succeed");
                return response(200, "ok");
            } catch (Exception e) {
                e.printStackTrace();
                logService.addOneLog(id, "user(id=" + id + ") update self info", "failed");
                return response(400, "update failed");
            }
        }
    }

    ;

}
