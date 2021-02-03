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
    public int addOneUser(User user) {
        int isAdd = 200;
        if(userDao.getUserByName(user.getName()) != null){
            isAdd = 401; //用户已存在
        }else{
            userDao.addOneUser(user);
        }
        return isAdd;
    }

    @Override
    public int login(String uid, String pwd) {
        User user = userDao.getUserById(uid);
        if(user == null){
            return 402; //用户不存在
        }else if(pwd.equals(user.getPwd())){
            return 200;
        }else{
            return 401; //密码错误
        }
    }
}
