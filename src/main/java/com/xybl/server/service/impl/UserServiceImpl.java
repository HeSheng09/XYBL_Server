package com.xybl.server.service.impl;

import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public boolean addOneUser(User user) {
        boolean isAdd = userDao.addOneUser(user);
        return isAdd;
    }

    @Override
    public boolean login(String uid, String pwd) {
        User user = userDao.getUserById(uid);
        if(pwd.equals(user.getPwd())){
            return true;
        }else{
            return false;
        }
    }
}
