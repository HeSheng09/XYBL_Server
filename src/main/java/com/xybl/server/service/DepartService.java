package com.xybl.server.service;

import com.xybl.server.entity.Department;

import java.util.List;

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

    /**
     * updateDepart
     * <p>根据部门id修改其个人信息</p>
     * @param New com.xybl.server.entity.Department.
     * @return void
     * @author liubocai
     * @create: 2021-03-10
     */
    public void updateDepart(Department New);

    /**
     * updateDmsch
     * <p>根据部门id修改其在r_dmsch_name中的中文名称</p>
     * @param nameCode java.lang.String.
     * @param newName java.lang.String.
     * @return void
     * @author liubocai
     * @create: 2021-03-10
     */
    public void updateDmsch(String nameCode, String newName);
}
