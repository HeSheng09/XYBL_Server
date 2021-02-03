package com.xybl.server.service.impl;

import com.xybl.server.dao.LogDao;
import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.utils.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * LogServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/2/3
 **/
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;

    @Override
    public void addOneLog(User user, String opr, String result) {
        logDao.addOneLog(LogUtil.genLog(user,opr,result));
    }
}
