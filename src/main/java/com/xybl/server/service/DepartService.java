package com.xybl.server.service;

import com.xybl.server.entity.Department;

public interface DepartService {
    /**
    * addDepartment
    * <p>添加部门</p>
    * @param depart com.xybl.server.entity.Department.
    * @return int
    * @author liubocai
    * @create: 2021-03-01
    */
    public int addDepartment(Department depart);

    /**
    * getDepartByName
    * <p>根据部门名获取部门完整信息</p>
    * @param name java.lang.String.
    * @return com.xybl.server.entity.Department
    * @author liubocai
    * @create: 2021-03-01
    */
    public Department getDepartByName(String name);

    /**
    * getDepartById
    * <p>根据id检索部门</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.Department
    * @author liubocai
    * @create: 2021-03-06
    */
    public Department getDepartById(String id);

    /**
    * delDepartById
    * <p>根据部门id删除部门</p>
    * @param id java.lang.String.
    * @return int
    * @author liubocai
    * @create: 2021-03-01
    */
    public int delDepartById(String id);
}
