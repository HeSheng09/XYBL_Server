package com.xybl.server.service;

import com.xybl.server.entity.User;

/**
 * UserService
 * <p></p>
 * @author liubocai
 * @create 2021/2/2
 **/
public interface UserService {

    /**
    * addOneUser
    * <p>添加用户</p>
    * @param user com.xybl.server.entity.User.
    * @return boolean
    * @author liubocai
    * @create: 2021/2/2
    */
    public boolean addOneUser(User user);

    /**
    * login
    * <p>登录</p>
    * @param uid java.lang.String.
     * @param pwd java.lang.String.
    * @return boolean
    * @author liubocai
    * @create: 2021/2/2
    */
    public boolean login(String uid, String pwd);

}
