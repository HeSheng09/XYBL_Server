package com.xybl.server.dao;

import com.xybl.server.entity.User;
import com.xybl.server.entity.Student;

import java.util.Map;

/**
 * UserDao
 * <p></p>
 * @author liubocai
 * @create 2021/2/2
 **/
public interface UserDao {

    /**
    * addOneUser
    * <p>添加一名用户信息。</p>
    * @param user com.xybl.server.entity.User.
    * @return boolean
    * @author liubocai
    * @create: 2021/2/2
    */
    public void addOneUser(User user);

    /**
    * addOneStu
    * <p>添加一名学生用户信息。</p>
    * @param stu com.xybl.server.entity.Student.
    * @author liubocai
    * @create: 2021-02-25
    */
    public void addOneStu(Student stu);

    /**
    * getUserById
    * <p>根据id查询用户</p>
    * @param uid java.lang.String.
    * @return com.xybl.server.entity.User
    * @author liubocai
    * @create: 2021/2/2
    */
    public User getUserById(String uid);

    /**
    * getUserByName
    * <p>根据用户名查询用户</p>
    * @param name java.lang.String.
    * @return com.xybl.server.entity.User
    * @author liubocai
    * @create: 2021-02-03
    */
    public User getUserByName(String name);

    /**
    * getLastUserId
    * <p>获取当天最新注册的用户id。</p>
    * @param  .
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/2/3
    */
    public Map<String,Object> getLastUserId();

    /**
    * deleteOneUser
    * <p>按id删除一个用户。此函数只会删除t_user表中的信息，而不会触及其它表。</p>
    * @param uid java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/1
    */
    public void deleteOneUser(String uid);

    /**
    * deleteOneStu
    * <p>按stu_id删除一条学生信息。</p>
    * @param stu_id java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/1
    */
    public void deleteOneStu(String stu_id);
}
