package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Department;
import com.xybl.server.entity.User;
import com.xybl.server.service.DepartService;
import com.xybl.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * DepartServiceImplTest
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
@SpringBootTest(classes = ServerApplication.class)
public class DepartServiceImplTest {
    @Resource
    private DepartService departService;
    @Resource
    private UserService userService;

    @Test
    void addDepartment(){
        Department department = new Department("湖北省教育厅");
        department.setId("HuBeiJiaoYu");
        department.setAddress("湖北省武汉市");
        department.setWeb("http://HuBeiJiaoYu.cn");
        department.setLevel("T");
        departService.addDepartment(department);
    }

    @Test
    void testGetById(){
        String id = "1615124188221017";
        String nid = userService.getDmschNidByNsUid(id);
        System.out.println(nid);
        Department depart = departService.getDepartById(nid.substring(0, 10) + "000");
        System.out.println(depart);
    }
}
