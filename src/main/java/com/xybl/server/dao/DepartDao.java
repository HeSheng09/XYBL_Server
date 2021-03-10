package com.xybl.server.dao;

import com.xybl.server.entity.Department;

import java.util.List;

public interface DepartDao {
    /**
    * addDepartment
    * <p>增加部门</p>
    * @param department com.xybl.server.entity.Department.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void addDepartment(Department department);

    /**
    * getDepartByName
    * <p>根据部门名获取部门所有信息</p>
    * @param name java.lang.String.
    * @return com.xybl.server.entity.Department
    * @author liubocai
    * @create: 2021-03-01
    */
    public Department getDepartByName(String name);

    /**
    * getDepartById
    * <p>根据部门ID获取部门所有信息</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.Department
    * @author liubocai
    * @create: 2021-03-01
    */
    public Department getDepartById(String id);

    /**
    * delDepartById
    * <p>根据部门id删除部门</p>
    * @param id java.lang.String.
    * @return void
    * @author liubocai
    * @create: 2021-03-01
    */
    public void delDepartById(String id);

    /**
    * getAllDepart
    * <p>获取所有部门详细信息</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.Department>
    * @author liubocai
    * @create: 2021-03-08
    */
    public List<Department> getAllDepart();

    /**
    * getAllNsUser
    * <p>根据部门nameCode获取其管理员id所有</p>
    * @param id java.lang.String.
    * @return java.util.List<java.lang.String>
    * @author liubocai
    * @create: 2021-03-10
    */
    public List<String> getAllNsUser(String id);
}
