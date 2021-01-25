package com.xybl.server.service.impl;

import com.xybl.server.dao.TestDao;
import com.xybl.server.entity.Test;
import com.xybl.server.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;
    @Override
    public Test test(int id) {
        return testDao.test(id);
    }
}
