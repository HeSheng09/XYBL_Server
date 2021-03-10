package com.xybl.server.dao;

import com.xybl.server.entity.School;

import java.util.List;

public interface SchoolDao {

    /**
    * addSchool
    * <p>添加学校</p>
    * @param school com.xybl.server.entity.School.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void addSchool(School school);

    /**
    * getSchoolByName
    * <p>根据学校名获取学校所有信息</p>
    * @param search_name java.lang.String. 
    * @return com.xybl.server.entity.School
    * @author liubocai
    * @create: 2021-03-01
    */
    public School getSchoolByName(String search_name);

    /**
    * delSchoolById
    * <p>根据学校id删除学校</p>
    * @param id java.lang.String.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void delSchoolById(String id);

    /**
    * getSchoolById
    * <p>根据学校id获取school对象</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.School
    * @author liubocai
    * @create: 2021-03-08
    */
    public School getSchoolById(String id);

    /**
    * getAllSchool
    * <p>获取所有学校</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.School>
    * @author liubocai
    * @create: 2021-03-08
    */
    public List<School> getAllSchool();
}
