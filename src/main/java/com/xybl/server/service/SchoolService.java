package com.xybl.server.service;

import com.xybl.server.entity.School;

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


}
