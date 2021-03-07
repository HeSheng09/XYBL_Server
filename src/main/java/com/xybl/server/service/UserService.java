package com.xybl.server.service;

import ch.qos.logback.core.joran.spi.NoAutoStartUtil;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;

import java.util.Map;

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
    public Map<String, Object> login(String uid, String pwd);

    /**
    * genId
    * <p>生成一个用户id。</p>
    * @param  .
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/2/8
    */
    public String genId();

    /**
     * getUserById
     * <p>根据id获取用户对象</p>
     * @param id java.lang.String.
     * @return com.xybl.server.entity.User
     * @author liubocai
     * @create: 2021-02-03
     */
    public User getUserById(String id);

    /**
    * getUserByName
    * <p>根据name获取用户对象</p>
    * @param name java.lang.String.
    * @return com.xybl.server.entity.User
    * @author liubocai
    * @create: 2021-03-06
    */
    public User getUserByName(String name);

    /**
    * addOneStu
    * <p>添加一名学生用户。</p>
    * @param stu com.xybl.server.entity.Student.
    * @return int
    * @author hesheng
    * @create: 2021/3/5
    */
    public int addOneStu(Student stu);

    /**
    * addOneNsu
    * <p>添加一名非学生用户</p>
    * @param nsu com.xybl.server.entity.NsUser.
    * @return int
    * @author liubocai
    * @create: 2021-03-01
    */
    public int addOneNsu(NsUser nsu);

    /**
    * delOneUserById
    * <p>根据id删除一个用户(user及stu、ns)。</p>
    * @param id java.lang.String.
    * @return int
    * @author liubocai
    * @create: 2021/3/5
    */
    public int delOneUserById(String id);

    /**
    * genNsUserName
    * <p>生成非学生用户的系统账号名</p>
    * @param authoName java.lang.String.
    * @return
    * @author liubocai
    * @create: 2021-03-06
    */
    public String genNsUserName(String authoName, String beNamed);

    /**
    * addOneDmschName
    * <p>向r_dmsch_name表中写入一条数据</p>
    * @param dmschName java.lang.String.
     * @param userName java.lang.String.
    * @return int
    * @author liubocai
    * @create: 2021-03-06
    */
    public int addOneDmschName(String dmschName, String userName);

    /**
    * getDmschName
     * * <p>根据机构或学校的在user表中的name代号来获取其中文名称</p>
    * @param nameCode java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-07
    */
    public String getDmschName(String nameCode);
}
