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
    * getStuById
    * <p>根据学生id获取信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.Student
    * @author liubocai
    * @create: 2021-03-08
    */
    public Student getStuById(String id);

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
    * getNsUserById
    * <p>根据用户id查询管理员账号的个人信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.NsUser
    * @author liubocai
    * @create: 2021-03-07
    */
    public NsUser getNsUserById(String id);

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

    /**
    * genNsUserPerName
    * <p>机构或学校给自己的用户分配账号，</p>
    * @param authoName java.lang.String.   授权机构的0级账号的t_user_name
     * @param privilege java.lang.String.  可选“1”，“2”，“3”
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-07
    */
    public String genNsUserPerName(String authoName, String privilege);

    /**
    * getSuperDmId
    * <p>根据ns_user id查询其上一级组织的id。</p>
    * @param ns_id java.lang.String.
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/3/7
    */
    public String getSuperDmId(String ns_id);

    /**
    * getSchIdByStuid
    * <p>根据学生id在r_sch_stu表中查询其所在学校的id</p>
    * @param stu_id java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-08
    */
    public String getSchIdByStuid(String stu_id);

    /**
     * updateStu
     * <p>更新学生个人信息</p>
     * @param New com.xybl.server.entity.Student.
     * @return void
     * @author liubocai
     * @create: 2021-03-10
     */
    public void updateStu(Student New);

    /**
     * updateNs
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

    /**
    * getDmschNidByNsUid
    * <p>在r_ns_dmsch表中根据非学生用户的uid查询其所在机构的nid</p>
    * @param nsUid java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-29
    */
    public String getDmschNidByNsUid(String nsUid);

    /**
    * getUidByNid
    * <p>根据管理员用户和机构用户的13位nameid获取其带时间戳的userid</p>
    * @param nid java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-04-01
    */
    public String getUidByNid(String nid);
}
