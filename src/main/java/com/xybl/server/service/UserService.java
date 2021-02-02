package com.xybl.server.service;

import com.xybl.server.entity.User;

public interface UserService {
    /**
    *@Description: 添加用户
    *@Params: user
    *@Returns: boolean
    *@author: liubocai
    *@Date: 2021-02-02
    */
    public boolean addOneUser(User user);

    /**
    *@Description: 登录
    *@Params:
    *@Returns:
    *@author: liubocai
    *@Date: 2021-02-02
    */
    public boolean login(String uid, String pwd);



}
