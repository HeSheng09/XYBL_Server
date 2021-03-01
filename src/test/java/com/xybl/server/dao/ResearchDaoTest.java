package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class ResearchDaoTest {
    @Resource
    private ResearchDao researchDao;

    @Test
    void getOneResearchById() {
        System.out.println(researchDao.getOneResearchById("1614607233000000"));
    }
}