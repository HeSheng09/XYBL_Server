package com.xybl.server.dao;

import com.xybl.server.entity.Appeal;

import java.util.List;
import java.util.Map;

/**
 * AppealDao
 * <p></p>
 * @author hesheng
 * @create 2021/2/2
 **/
public interface AppealDao {
    /**
    * getOneAppealById
    * <p>根据Appeal id查询一条Appeal。</p>
    * @param id int. Appeal id.
    * @return com.xybl.server.entity.Appeal
    * @author hesheng
    * @create: 2021/2/2
    */
    public Appeal getOneAppealById(String id);

    /**
    * getAppeals
    * <p>查询多条Appeal（最多100条）。</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/2/2
    */
    public List<Appeal> getAppeals();

    /**
    * addOneAppeal
    * <p>新增一条Appeal。</p>
    * @param appeal com.xybl.server.entity.Appeal.
    * @return void
    * @author hesheng
    * @create: 2021/2/2
    */
    public void addOneAppeal(Appeal appeal);

    /**
    * deleteOneAppealById
    * <p>根据Appeal id删除一条Appeal。</p>
    * @param id java.lang.String. Appeal id.
    * @return void
    * @author hesheng
    * @create: 2021/2/2
    */
    public void deleteOneAppealById(String id);

    /**
    * updateOneAppeal
    * <p>更新某条Appeal。</p>
    * @param appeal com.xybl.server.entity.Appeal.
    * @return void
    * @author hesheng
    * @create: 2021/2/2
    */
    public void updateOneAppeal(Appeal appeal);

    /**
    * getLastId
    * <p>获取当日的上一条ID。</p>
    * @param  .
    * @return int
    * @author hesheng
    * @create: 2021/3/1
    */
    public Map<String,Object> getLastId();
}
