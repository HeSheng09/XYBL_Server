package com.xybl.server.service.impl;

import com.xybl.server.dao.SchoolDao;
import com.xybl.server.entity.School;
import com.xybl.server.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SchoolServiceImpl
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
@Service("SchoolService")
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolDao schoolDao;

    @Override
    public boolean addSchool(School sch) {
        if(schoolDao.getSchoolByName(sch.getName()) != null){
            return false;
        }else {
            schoolDao.addSchool(sch);
            return true;
        }
    }

    @Override
    public School getSchoolByName(String name) {
        if(schoolDao.getSchoolByName(name) != null){
            return schoolDao.getSchoolByName(name);
        }else{
            return null;
        }
    }

    @Override
    public School getSchoolById(String id) {
        return schoolDao.getSchoolById(id);
    }

    @Override
    public String delSchoolById(String id) {
        schoolDao.delSchoolById(id);
        return "done";
    }

    @Override
    public List<School> getAllSch() {
        return schoolDao.getAllSchool();
    }

    @Override
    public List<String> getAllStu(String sch_id) {
        return schoolDao.getAllStu(sch_id);
    }

    @Override
    public void updateSchool(School New) {
        schoolDao.updateSchool(New);
    }

    @Override
    public void addOneStu(String schId, String stuId) {
        schoolDao.addOneStu(schId, stuId);
    }
}
