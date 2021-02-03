package com.xybl.server.service;

import com.xybl.server.entity.User;

/**
 * LogService
 * <p></p>
 * @author hesheng
 * @create 2021/2/3
 **/
public interface LogService {
    /**
    * addOneLog
    * <p>添加一条日志到数据库中。</p>
    * @param user com.xybl.server.entity.User.
     * @param opr java.lang.String.
     * @param result java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/2/3
    */
    public void addOneLog(User user,String opr,String result);
}
