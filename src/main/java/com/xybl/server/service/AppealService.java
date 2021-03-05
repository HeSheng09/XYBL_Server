package com.xybl.server.service;

import com.xybl.server.entity.Appeal;

import java.util.List;

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
    * getOneAppealById
    * <p>根据appeal id获取一条Appeal。</p>
    * @param al_id java.lang.String.
    * @return com.xybl.server.entity.Appeal
    * @author hesheng
    * @create: 2021/3/5
    */
    public Appeal getOneAppealById(String al_id);

    /**
    * addOneAppeal
    * <p>添加一条Appeal到数据库中。</p>
    * @param appeal com.xybl.server.entity.Appeal.
    * @return void
    * @author hesheng
    * @create: 2021/3/5
    */
    public void addOneAppeal(Appeal appeal,String handler);

    public List<Appeal> getAppealsUnderManagement(String user_id);
}
