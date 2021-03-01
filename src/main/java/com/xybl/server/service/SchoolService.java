package com.xybl.server.service;

import com.xybl.server.entity.School;

public interface SchoolService {
    public boolean addSchool(School sch);

    public School getSchoolByName(String name);

    public String delSchoolById(String id);
}
