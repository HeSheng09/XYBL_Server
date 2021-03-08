package com.xybl.server.controller;

import com.xybl.server.entity.School;
import com.xybl.server.service.DepartService;
import com.xybl.server.service.SchoolService;
import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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



    @GetMapping("/add")
    public Map<String, Object> addSchoolTest(){
        School testSchool = new School("武汉大学");
        testSchool.setAddress("武汉珞珈山");
        testSchool.setId("test_whu_id");
        testSchool.setPostcode("410000");
        testSchool.setTel("12345679");
        testSchool.setWeb("http://whu.edu.cn");
        if(schoolService.addSchool(testSchool)){
            return response(200, "ok");
        }else{
            return response(401, "用户已存在");
        }
    }

    @GetMapping("/get")
    public Map<String, Object> getSchool(){
        School t = schoolService.getSchoolByName("武汉大学");
        if(t != null){
            return response(200, t.toString());
        }else{
            return response(401, "not find");
        }
    }

    @GetMapping("/del")
    public Map<String, Object> delSchool(){
        String result = schoolService.delSchoolById("test_whu_id");
        return response(401, result);

    }
}
