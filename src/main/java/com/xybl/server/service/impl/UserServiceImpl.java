package com.xybl.server.service.impl;

import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

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
    public int login(String uid, String pwd) {
        User user = userDao.getUserById(uid);
        if (user == null) {
            return 402; //用户不存在
        } else if (pwd.equals(user.getPwd())) {
            return 200;
        } else {
            return 401; //密码错误
        }
    }

    @Override
    public int getLast_id() {
        Map<String, Object> map = userDao.getLastUserId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return last_id;
    }

    @Override
    public String genId() {
        return IDUtil.genId(getLast_id());
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
