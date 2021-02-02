package com.xybl.server.dao;

import com.xybl.server.entity.Test;

/**
 * TestDao
 * <p></p>
 * @author hesheng
 * @create 2021/2/2
 **/
public interface TestDao {
    /**
    * test
    * <p>测试：按照id查询一条test数据。</p>
    * @param id int.
    * @return com.xybl.server.entity.Test
    * @author hesheng
    * @create: 2021/2/2
    */
    public Test test(int id);

    /**
    * delete
    * <p>测试：按照id删除一条test数据。</p>
    * @param id int.
    * @return void
    * @author hesheng
    * @create: 2021/2/2
    */
    public void delete(int id);
}
