package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.service.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class LogServiceImplTest {

    @Resource
    private LogService logService;
    @Test
    void addOneLog() {
        logService.addOneLog("1612717415512000","new log service test", "succeed!");
    }
}