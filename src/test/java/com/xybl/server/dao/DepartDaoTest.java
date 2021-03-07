package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * DepartDaoTest
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
@SpringBootTest(classes = ServerApplication.class)
public class DepartDaoTest {
    @Resource
    private DepartDao departDao;

    @Test
    void addOneDepart(){
        Department department = new Department("湖北省教育厅");
        department.setId("HuBeiJiaoYu");
        department.setAddress("湖北省武汉市");
        department.setWeb("http://HuBeiJiaoYu.cn");
        department.setLevel("T");
        System.out.println(department.toString());
        departDao.addDepartment(department);
    }


}
