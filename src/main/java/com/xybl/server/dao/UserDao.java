package com.xybl.server.dao;

import com.xybl.server.entity.NsUser;
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
    * getStuById
    * <p>确定是学生用户后，根据其id查询该学生详细信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.Student
    * @author liubocai
    * @create: 2021-03-07
    */
    public Student getStuById(String id);

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

    /**
    * addOneNsu
    * <p>添加一名非学生用户</p>
    * @param nsu com.xybl.server.entity.NsUser.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void addOneNsu(NsUser nsu);

    /**
    * delOneNsu
    * <p>删除一名非学生用户</p>
    * @param nsu_id java.lang.String.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void delOneNsu(String nsu_id);

    /**
    * getLastNsName
    * <p></p>
    * @param sameFront java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-06
    */
    public String getLastNsNameNum(String sameFront);

    /**
    * getNsUserById
    * <p>根据管理员id获取其NsUser账号的信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.NsUser
    * @author liubocai
    * @create: 2021-03-07
    */
    public NsUser getNsUserById(String id);

    /**
    * getLastDmschNameNum
    * <p>获取r_dmsch_name表中某一级别的最高序号</p>
    * @param searchPart java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-06
    */
    public String getLastDmschNameNum(String searchPart);

    /**
    * addOneDmschName
    * <p>向r_dmsch_name表中存入新加0级机构的实名以及其在user中的name代号</p>
    * @param beNamed java.lang.String.
     * @param nameCode java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-06
    */
    public void addOneDmschName(String beNamed, String nameCode);

    /**
    * getDmschName
    * <p>根据机构或学校的在user表中的name代号来获取其中文名称</p>
    * @param nameCode java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-07
    */
    public String getDmschName(String nameCode);

    /**
    * getSchIdByStuid
    * <p>根据学生id在r_sch_stu表中查询其所在学校的id</p>
    * @param id java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-08
    */
    public String getSchIdByStuid(String id);

    /**
    * updateStuByid
    * <p>更新学生个人信息</p>
    * @param New com.xybl.server.entity.Student.
    * @return void
    * @author liubocai
    * @create: 2021-03-10
    */
    public void updateStu(Student New);

    /**
    * updateNsByid
    * <p>更新管理员信息</p>
    * @param New com.xybl.server.entity.NsUser.
    * @return void
    * @author liubocai
    * @create: 2021-03-10
    */
    public void updateNs(NsUser New);

    /**
    * updateUser
    * <p>更新User表用户名或密码</p>
    * @param New com.xybl.server.entity.User.
    * @return void
    * @author liubocai
    * @create: 2021-03-10
    */
    public void updateUser(User New);
}
