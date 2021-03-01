package com.xybl.server.service;

/**
 * AppealService
 * <p></p>
 * @author hesheng
 * @create 2021/3/1
 **/
public interface AppealService {
    /**
    * genAppealId
    * <p>生成一个Appeal ID。</p>
    * @param  .
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/3/1
    */
    public String genAppealId();

    /**
    * getLastId
    * <p>获取当日的上一条id。</p>
    * @param  .
    * @return int
    * @author hesheng
    * @create: 2021/3/1
    */
    public int getLastId();
}
