package com.xybl.server.dao;

import com.xybl.server.entity.Log;

import java.util.List;

/**
 * LogDao
 * <p></p>
 * @author hesheng
 * @create 2021/2/2
 **/
public interface LogDao {
    /**
    * addOneLog
    * <p>插入一条日志。</p>
    * @param log com.xybl.server.entity.Log.
    * @return void
    * @author hesheng
    * @create: 2021/2/2
    */
    public void addOneLog(Log log);

    /**
    * getLogs
    * <p>查询最近100条日志。</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.Log>
    * @author hesheng
    * @create: 2021/2/2
    */
    public List<Log> getLogs();
}
