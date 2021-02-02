package com.xybl.server.service;

import com.xybl.server.entity.Test;

/**
 * TestService
 * <p></p>
 * @author hesheng
 * @create 2021/2/2
 **/
public interface TestService {
    /**
    * test
    * <p>测试：根据id获得Test实体。</p>
    * @param id int.
    * @return com.xybl.server.entity.Test
    * @author hesheng
    * @create: 2021/2/2
    */
    public Test test(int id);
}
