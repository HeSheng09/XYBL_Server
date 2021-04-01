package com.xybl.server.controller;

import com.xybl.server.entity.Department;
import com.xybl.server.entity.School;
import com.xybl.server.entity.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * SchoolController
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
@RestController
@RequestMapping("/dmsch")
public class DmschController {
    @Resource
    private SchoolService schoolService;
    @Resource
    private DepartService departService;
    @Resource
    private UserService userService;
    @Resource
    private LogService logService;


    @RequestMapping("/getallsch")
    public Map<String, Object> getAllSchool(@RequestParam(name="user_id")String user_id){
        List<School> schools = schoolService.getAllSch();
        logService.addOneLog(user_id, "get all schools info","ok");
        return response(200, "ok", schools);
    }

    @RequestMapping("/getallde")
    public Map<String, Object> getAllDepartment(@RequestParam(name="user_id")String user_id){
        List<Department> departments = departService.getAllDepart();
        logService.addOneLog(user_id, "get all departments info","ok");
        return response(200, "ok", departments);
    }

    @RequestMapping("/getalluser")
    public Map<String, Object> getAllStu(@RequestParam(name="id")String id,
                                         @RequestParam(name="role")String role)
    {
        User user = userService.getUserById(id);
        if("false".equals(role)){
            try{
                List<String> stus =  schoolService.getAllStu(user.getName());
                logService.addOneLog(id, "user(id="+id+")get all students' id", "succeed");
                return response(200, "ok", stus);
            }catch (Exception e){
                e.printStackTrace();
                logService.addOneLog(id, "user(id="+id+")get all students' id", "failed");
                return response(400, "failed");
            }
        }else{
            try{
                List<String> nsUser =  departService.getAllNsUser(user.getName());
                logService.addOneLog(id, "user(id="+id+")get all nsUsers' id", "succeed");
                return response(200, "ok", nsUser);
            }catch (Exception e){
                e.printStackTrace();
                logService.addOneLog(id, "user(id="+id+")get all nsUsers' id", "failed");
                return response(400, "failed");
            }
        }
    }

    @RequestMapping("/get")
    public Map<String, Object> get(@RequestParam(name="id")String id)
    {
        User user = userService.getUserById(id);
        String nameCode = user.getName();
        if("000".equals(nameCode.substring(7,10))){//判断不是学校
            logService.addOneLog(id, "get dmsch's info", "succeed");
            return response(200, "ok", departService.getDepartById(nameCode));
        }else{
            logService.addOneLog(id, "get dmsch's info", "succeed");
            return response(200, "ok", schoolService.getSchoolById(nameCode));
        }
    }

    @RequestMapping("/update")
    public Map<String, Object> update(@RequestParam(name = "id")String id,
                                      @RequestParam(name = "pwd", defaultValue = "not_provided")String pwd,
                                      @RequestParam(name = "dName", defaultValue = "not_provided")String dName,
                                      @RequestParam(name = "address", defaultValue = "not_provided")String address,
                                      @RequestParam(name = "web", defaultValue = "not_provided")String web,
                                      @RequestParam(name = "postcode", defaultValue = "not_provided")String postcode,
                                      @RequestParam(name = "tel", defaultValue = "not_provided")String tel)
    {
        String nameCode = userService.getUserById(id).getName();
        if(!"not_provided".equals(pwd)){
            String enPwd = MD5Util.getEncryptedText(pwd);
            userService.updateUser(new User(id,enPwd));
        }
        if(!"not_provided".equals(dName)){
            departService.updateDmsch(dName, nameCode);
        }
        if("000".equals(userService.getUserById(id).getName().substring(7,10))){
            Department department = new Department(nameCode);
            if(!"not_provided".equals(dName)){
                department.setName(dName);
            }
            if(!"not_provided".equals(address)){
                department.setAddress(address);
            }
            if(!"not_provided".equals(web)){
                department.setWeb(web);
            }
            try{
                departService.updateDepart(department);
                logService.addOneLog(id, "update self info", "succeed");
                return response(200, "ok");
            }catch (Exception e) {
                e.printStackTrace();
                logService.addOneLog(id, "update self info", "failed");
                return response(400, "update failed");
            }
        }else {
            School school = new School(nameCode);
            if(!"not_provided".equals(dName)){
                school.setName(dName);
            }
            if(!"not_provided".equals(address)){
                school.setAddress(address);
            }
            if(!"not_provided".equals(web)){
                school.setWeb(web);
            }
            if(!"not_provided".equals(postcode)){
                school.setPostcode(postcode);
            }
            if(!"not_provided".equals(tel)){
                school.setTel(tel);
            }
            try{
                schoolService.updateSchool(school);
                logService.addOneLog(id, "update self info", "succeed");
                return response(200, "ok");
            }catch (Exception e) {
                e.printStackTrace();
                logService.addOneLog(id, "update self info", "failed");
                return response(400, "update failed");
            }
        }
    }

    @RequestMapping("/getsuperdm")
    Map<String, Object> getAllSuperDm(@RequestParam(name = "id")String id){
        //1.接收参数
        User tuser = userService.getUserById(id);
        String tname = tuser.getName();
        //2.查询super机构
        List<String> tnameList = new ArrayList<>();
        List<String> uidList = new ArrayList<>();
        String s1 = tname.substring(0, 2);
        String s2 = tname.substring(2, 4);
        String s3 = tname.substring(4, 7);
        String s4 = tname.substring(7, 10);
        String s5 = tname.substring(10, 13);

        if(!"00".equals(s1)){
            tnameList.add("0000000000000");
        }
        if(!"00".equals(s2)){
            tnameList.add(s1+"00000000000");
        }
        if(!"000".equals(s3)){
            tnameList.add(s1+s2+"000000000");
        }
        if(!"000".equals(s4)){
            tnameList.add(s1+s2+s3+"000000");
        }
        if(!"000".equals(s5)){
            tnameList.add(s1+s2+s3+s4+"000");
        }


        for(String s : tnameList){
            String t = userService.getUidByNid(s);
            if(t != null){
                uidList.add(t);
            }
        }
        return response(200, "ok", uidList);
    }
}
