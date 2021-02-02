package com.xybl.server.service.impl;

import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public boolean addOneUser(User user) {
        return false;
    }

    @Override
    public boolean login(String uid, String pwd) {
        User user = userDao.getUserById(uid);
        return true;

    }
}
