package com.xybl.server.service;

import com.xybl.server.entity.Appeal;

import java.util.List;

/**
 * AppealService
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
public interface AppealService {
    /**
     * genAppealId
     * <p>生成一个Appeal ID。</p>
     *
     * @param .
     * @return java.lang.String
     * @author hesheng
     * @create: 2021/3/1
     */
    public String genAppealId();

    /**
     * getOneAppealById
     * <p>根据appeal id获取一条Appeal。</p>
     *
     * @param al_id java.lang.String.
     * @return com.xybl.server.entity.Appeal
     * @author hesheng
     * @create: 2021/3/5
     */
    public Appeal getOneAppealById(String al_id);

    /**
     * addOneAppeal
     * <p>添加一条Appeal到数据库中。</p>
     *
     * @param appeal  com.xybl.server.entity.Appeal. 举报信息。
     * @param handler java.lang.String. 受理学校（部门）的（机构）账户id。
     * @return void
     * @throws Exception
     * @author hesheng
     * @create: 2021/3/6
     */
    public void addOneAppeal(Appeal appeal, String handler) throws Exception;

    /**
     * getAppealsUnderManagement
     * <p>通过Ns_User id查询提交到其组织所在单位下的appeal。</p>
     *
     * @param user_id java.lang.String. Ns_User的user_id。
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/6
     */
    public List<Appeal> getAppealsUnderManagement(String user_id);

    /**
     * deleteOneAppealById
     * <p>根据al_id和user_id删除一条appeal。注意：只有本人可以删除。</p>
     *
     * @param user_id java.lang.String.
     * @param al_id   java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/6
     */
    public void deleteOneAppealById(String user_id, String al_id);

    /**
     * updateOneAppealById
     * <p>根据id修改一条Appeal的部分内容。注意：只有本人可以进行修改。</p>
     *
     * @param appeal com.xybl.server.entity.Appeal.
     * @return void
     * @author hesheng
     * @create: 2021/3/6
     */
    public void updateOneAppealById(Appeal appeal);

    /**
     * getAppealsByUserId
     * <p>根据user_id(student)获取该用户所提交的所有的appeal。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/6
     */
    public List<Appeal> getAppealsByUserId(String user_id);

    /**
     * getUnWatchedAppealsByStu_id
     * <p>根据学生id获取未受理的所有举报。</p>
     *
     * @param stu_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/8
     */
    public List<Appeal> getUnWatchedAppealsByStu_id(String stu_id);

    /**
     * getUnWatchedAppealsByNs_id
     * <p>根据非学生用户id获取未受理的所有举报。</p>
     *
     * @param ns_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/8
     */
    public List<Appeal> getUnWatchedAppealsByNs_id(String ns_id);

    /**
     * getWatchedAppealsByStu_id
     * <p>学生查询已受理的所有举报（包括已得出结果和未得出结果的。）</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getWatchedAppealsByStu_id(String user_id);

    /**
     * getNoResultAppealsByStu_id
     * <p>学生查询已受理，但未得出结果的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getNoResultAppealsByStu_id(String user_id);

    /**
     * getHasResultAppealsByStu_id
     * <p>学生查询已经得出结果的所有举报</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getHasResultAppealsByStu_id(String user_id);

    /**
     * getRe_appealedAppealsByStu_id
     * <p>学生查询所有进行了二次举报的举报，即查询结果为初次举报的信息。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getRe_appealedAppealsByStu_id(String user_id);

    /**
     * getWatchedAppealsByNs_id
     * <p>非学生用户查询所在单位已受理的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getWatchedAppealsByNs_id(String user_id);

    /**
     * getNoResultAppealsByNs_id
     * <p>非学生用户查询所在单位已受理，但未得出结果的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getNoResultAppealsByNs_id(String user_id);

    /**
     * getHasResultAppealsByNs_id
     * <p>非学生用户查询所在单位已给出调查结果的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> getHasResultAppealsByNs_id(String user_id);

    /**
     * searchForAppealsByKeywords
     * <p>根据关键词查询举报。</p>
     *
     * @param user_id java.lang.String.
     * @param keys    java.lang.String.
     * @return java.util.List<com.xybl.server.entity.Appeal>
     * @author hesheng
     * @create: 2021/3/10
     */
    List<Appeal> searchForAppealsByKeywords(String user_id, String keys);
}
