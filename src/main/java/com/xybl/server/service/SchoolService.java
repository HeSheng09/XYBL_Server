package com.xybl.server.service;

import com.xybl.server.entity.School;

import java.util.List;

public interface SchoolService {
    /**
    * addSchool
    * <p>添加学校</p>
    * @param sch com.xybl.server.entity.School.
    * @return boolean
    * @author liubocai
    * @create: 2021-03-01
    */
    public boolean addSchool(School sch);

    /**
    * getSchoolByName
    * <p>根据学校名获取学校信息</p>
    * @param name java.lang.String.
    * @return com.xybl.server.entity.School
    * @author liubocai
    * @create: 2021-03-01
    */
    public School getSchoolByName(String name);

    /**
    * getSchoolById
    * <p>根据学校id获取学校信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.School
    * @author liubocai
    * @create: 2021-03-08
    */
    public School getSchoolById(String id);

    /**
    * delSchoolById
    * <p>根据学校id删除学校</p>
    * @param id java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-03-01
    */
    public String delSchoolById(String id);

    /**
    * getAllSch
    * <p>获取所有的学校</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.School>
    * @author liubocai
    * @create: 2021-03-08
    */
    public List<School> getAllSch();

    /**
     * getAllStu
     * <p>根据学校id获取其所有学生id</p>
     * @param sch_id java.lang.String.
     * @return java.util.List<java.lang.String>
     * @author liubocai
     * @create: 2021-03-10
     */
    public List<String> getAllStu(String sch_id);
}
