package com.xybl.server.dao;

import com.xybl.server.entity.User;

public interface UserDao {
    /**
    *@Description: 添加一名用户
    *@Params:
    *@Returns:
    *@author: liubocai
    *@Date: 2021-02-02
    */
    public boolean addOneUser(User user);

    /**
    *@Description: 根据id查询用户
    *@Params:
    *@Returns:
    *@author: liubocai
    *@Date: 2021-02-02
    */
    public User getUserById(String uid);

}
