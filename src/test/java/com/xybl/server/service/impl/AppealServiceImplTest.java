package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.service.AppealService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class AppealServiceImplTest {
    @Resource
    private AppealService appealService;

    @Test
    void genAppealId() {
        System.out.println(appealService.genAppealId());
    }
}