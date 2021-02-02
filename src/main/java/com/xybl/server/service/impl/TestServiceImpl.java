package com.xybl.server.service.impl;

import com.xybl.server.dao.TestDao;
import com.xybl.server.entity.Test;
import com.xybl.server.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TestServiceImpl
 * <p></p>
 * @author hesheng
 * @create 2021/2/2
 **/
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public Test test(int id) {
        //
        return testDao.test(id);
    }
}
