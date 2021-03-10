package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * SchoolDaoTest
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-08
 **/
@SpringBootTest(classes = ServerApplication.class)
class SchoolDaoTest {
    @Resource
    private SchoolDao schoolDao;

    @Test
    void getSchById(){
        School school = schoolDao.getSchoolById("0101001001000");
        System.out.println(school);
    }

    @Test
    void getAllSch(){
        List<School> schools = schoolDao.getAllSchool();
        System.out.println(schools);
    }

    @Test
    void getAllStu(){
        List<String> stus = schoolDao.getAllStu("0101001001000");
        System.out.println(stus);
    }
}
