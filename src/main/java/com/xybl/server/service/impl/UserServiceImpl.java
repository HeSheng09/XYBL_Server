package com.xybl.server.service.impl;

import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.IDUtil;
import com.xybl.server.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public int addOneUser(User user) {
        int isAdd = 200;
        if (userDao.getUserByName(user.getName()) != null) {
            isAdd = 401; //用户已存在
        } else {
            userDao.addOneUser(user);
        }
        return isAdd;
    }

    @Override
    public int addOneStu(Student stu) {
        int isAdd = 200;
        if (userDao.getUserById(stu.getId()) != null) {
            isAdd = 401;//用户已存在
        } else {
            try{
                userDao.addOneUser(new User(stu.getId(), stu.getName(), stu.getPwd(), false));
                userDao.addOneStu(stu);
            }catch (Exception e){
                // 插库出错，删除插入的数据
                userDao.deleteOneStu(stu.getId());
                userDao.deleteOneUser(stu.getId());
                isAdd=500;//系统错误
            }
        }
        return isAdd;
    }

    @Override
    public Map<String, Object> login(String name, String pwd) {
        User user = userDao.getUserByName(name);
        if (user == null) {
            return response(400, "nouser"); //用户不存在
        } else if (MD5Util.validText(pwd, user.getPwd())) {
            return response(200, String.valueOf(user.getRole())); //密码正确
        } else {
            return response(401, "pwderror"); //密码错误
        }
    }

    @Override
    public String genId() {
        Map<String, Object> map = userDao.getLastUserId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public int addOneNsu(NsUser nsu) {
        int isAdd = 200;
        if(userDao.getUserById(nsu.getId()) != null){
            isAdd = 401; //用户已存在
        }else{
            try{
                userDao.addOneUser(new User(nsu.getId(), nsu.getName(), nsu.getPwd(), true));
                userDao.addOneNsu(nsu);
            }catch (Exception e){
                // 插库出错， 删除插入的数据
                userDao.delOneNsu(nsu.getId());
                userDao.deleteOneUser(nsu.getId());
                isAdd = 500;//系统错误
            }
        }
        return isAdd;
    }

    @Override
    public int delOneUserById(String id) {
        int isDel = 200;
        if(userDao.getUserById(id) != null){
            userDao.deleteOneUser(id);
            if(userDao.getUserById(id).getRole() == true){
                userDao.delOneNsu(id);
            }else{
                userDao.deleteOneStu(id);
            }
            return isDel; //成功删除
        }else{
            return 401;//用户不存在
        }
    }
}
