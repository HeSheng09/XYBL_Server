package com.xybl.server.dao;

import com.xybl.server.entity.Appeal;

import java.util.List;
import java.util.Map;

/**
 * AppealDao
 * <p></p>
 *
 * @author hesheng
 * @create 2021/2/2
 **/
public interface AppealDao {
    /**
     * getOneAppealById
     * <p>根据Appeal id查询一条Appeal。</p>
     *
     * @param id int. Appeal id.
     * @return com.xybl.server.entity.Appeal
     * @author hesheng
     * @create: 2021/2/2
     */
    public Appeal getOneAppealById(String id);

    /**
     * getOneAppealByAlUserId
     * <p>根据appeal id和student id查询得到一条Appeal。</p>
     *
     * @param al_id   java.lang.String.
     * @param user_id java.lang.String.
     * @return com.xybl.server.entity.Appeal
     * @author hesheng
     * @create: 2021/3/6
     */
    public Appeal getOneAppealByAlUserId(String al_id, String user_id);

    /**
     * addRelAlHandler
     * <p>向r_al_ns表中插入一条关系。</p>
     *
     * @param al_id    java.lang.String.
     * @param smsch_id java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/6
     */
    public void addRelAlHandler(String al_id, String dmsch_id);

    /**
     * deleteRelAlNsByAlId
     * <p>删除一条关系。注意：只有本人可以删除。</p>
     *
     * @param al_id   java.lang.String.
     * @param user_id java.lang.String.
     * @return .
     * @author hesheng
     * @create: 2021/3/6
     */
    public void deleteRelAlNsByAlId(String al_id, String user_id);

    /**
     * getAppealsByDmSchId
     * <p>通过部门或学校的（机构）帐号id查询相关的appeal。</p>
     *
     * @param dmsch_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/6
     */
    public List<Appeal> getAppealsByDmSchId(String dmsch_id);

    /**
     * getAppeals
     * <p>查询多条Appeal（最多100条）。</p>
     *
     * @param .
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/2/2
     */
    public List<Appeal> getAppeals();

    /**
     * addOneAppeal
     * <p>新增一条Appeal。</p>
     *
     * @param appeal com.xybl.server.entity.Appeal.
     * @return void
     * @author hesheng
     * @create: 2021/2/2
     */
    public void addOneAppeal(Appeal appeal);

    /**
     * deleteOneAppealById
     * <p>根据Appeal id删除一条Appeal。注意：只有本人可以删除。</p>
     *
     * @param id      java.lang.String.
     * @param user_id java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/6
     */
    public void deleteOneAppealById(String id, String user_id);

    /**
     * updateOneAppeal
     * <p>根据id更新某条Appeal。注意：只有本人可以进行修改。</p>
     *
     * @param appeal com.xybl.server.entity.Appeal.
     * @return void
     * @author hesheng
     * @create: 2021/2/2
     */
    public void updateOneAppeal(Appeal appeal);

    /**
     * getLastId
     * <p>获取当天的上一条ID。</p>
     *
     * @param .
     * @return int
     * @author hesheng
     * @create: 2021/3/1
     */
    public Map<String, Object> getLastId();

    /**
    * getAppealsByUser_id
    * <p>根据user_id(student)获取该用户所提交的所有的appeal。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/6
    */
    public List<Appeal> getAppealsByUser_id(String user_id);
}
