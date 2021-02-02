package com.xybl.server.dao;

import com.xybl.server.entity.Test;

public interface TestDao {
    public Test test(int id);
    public void delete(int id);
}
