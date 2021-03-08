package com.xybl.server.service.impl;

import com.xybl.server.ServerApplication;
import com.xybl.server.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class RouteServiceImplTest {
    @Resource
    private RouteService routeService;

    @Test
    void getOneCompleteRoute() {
        Map<String,Object> com_route=routeService.getOneCompleteRoute("1615128917872000");
        System.out.println(com_route);
    }

    @Test
    void getCurRoute() {
    }

    @Test
    void getFollowRoutes() {
    }
}