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
     * @param appeal com.xybl.server.entity.Appeal. 举报信息。
     * @param handler java.lang.String. 受理学校（部门）的（机构）账户id。
    * @return void
    * @throws Exception
    * @author hesheng
    * @create: 2021/3/6
    */
    public void addOneAppeal(Appeal appeal,String handler) throws Exception;

    /**
    * getAppealsUnderManagement
    * <p>通过Ns_User id查询提交到其组织所在单位下的appeal。</p>
    * @param user_id java.lang.String. Ns_User的user_id。
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/6
    */
    public List<Appeal> getAppealsUnderManagement(String user_id);

    /**
    * deleteOneAppealById
    * <p>根据al_id和user_id删除一条appeal。注意：只有本人可以删除。</p>
    * @param user_id java.lang.String.
     * @param al_id java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/6
    */
    public void deleteOneAppealById(String user_id,String al_id);

    /**
    * updateOneAppealById
    * <p>根据id修改一条Appeal的部分内容。注意：只有本人可以进行修改。</p>
    * @param appeal com.xybl.server.entity.Appeal.
    * @return void
    * @author hesheng
    * @create: 2021/3/6
    */
    public void updateOneAppealById(Appeal appeal);
}
