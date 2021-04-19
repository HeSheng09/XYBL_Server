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

    @Override
    public List<String> getAllNsUser(String id) {
        return departDao.getAllNsUser(id);
    }

    @Override
    public void updateDepart(Department New) {
        departDao.updateDepart(New);
    }

    @Override
    public void updateDmsch(String nameCode, String newName) {
        departDao.updateDmsch(nameCode, newName);
    }

    @Override
    public void addOneNs(String dmschid, String nsUid) {
        departDao.addOneNs(dmschid, nsUid);
    }

    @Override
    public void addOneSch(String departid, String schid) {
        departDao.addOneSch(departid, schid);
    }

    @Override
    public String getNameByNid(String nid) {
        return departDao.getNameByNid(nid);
    }
}
