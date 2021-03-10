package com.xybl.server.service.impl;

import com.xybl.server.dao.DepartDao;
import com.xybl.server.entity.Department;
import com.xybl.server.service.DepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * DepartServiceImpl
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
@Service("departService")
public class DepartServiceImpl implements DepartService {
    @Resource
    private DepartDao departDao;

    @Override
    public int addDepartment(Department depart) {
        if(departDao.getDepartByName(depart.getName()) != null){
            return 401; //学校用户已存在
        }else{
            departDao.addDepartment(depart);
            return 200; //添加成功
        }

    }

    @Override
    public Department getDepartByName(String name) {
        return departDao.getDepartByName(name);
    }

    @Override
    public Department getDepartById(String id) {
        return departDao.getDepartById(id);
    }

    @Override
    public int delDepartById(String id) {
        if(departDao.getDepartById(id) != null){
            departDao.delDepartById(id);
            return 200; //has been deleted
        }else{
            return 401; //部门不存在
        }
    }

    @Override
    public List<Department> getAllDepart() {
        return departDao.getAllDepart();
    }
}
