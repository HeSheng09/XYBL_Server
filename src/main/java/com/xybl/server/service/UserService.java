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
   * <p>添加一名用户</p>
   * @param user com.xybl.server.entity.User.
   * @return int
   * @author liubocai
   * @create: 2021-02-03
   */
    public int addOneUser(User user);

    /**
    * login
    * <p>登录</p>
    * @param uid java.lang.String.
     * @param pwd java.lang.String.
    * @return int
    * @author liubocai
    * @create: 2021/2/2
    */
    public int login(String uid, String pwd);

    /**
    * getLast_id
    * <p>获取当天最新注册的用户id。</p>
    * @param  . 
    * @return int
    * @author hesheng
    * @create: 2021/2/3
    */
    public int getLast_id();

    /**
    * genId
    * <p>生成一个用户id。</p>
    * @param  .
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/2/8
    */
    public String genId();
}
