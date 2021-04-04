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
     * @param dmsch_id java.lang.String.
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
    * getAppealsByNsUserId
    * <p>根据非学生用户的id获取其部门的所有相关Appeal</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/7
    */
    public List<Appeal> getAppealsByNsUserId(String user_id,boolean is_first);

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

    /**
    * getUnWatchedAppealsByStu_id
    * <p>根据学生id获取未受理的所有举报。</p>
    * @param stu_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/8
    */
    public List<Appeal> getUnWatchedAppealsByStu_id(String stu_id,boolean is_first);

    /**
    * getUnWatchedAppealsByNs_id
    * <p>根据非学生用户id获取未受理的所有举报。</p>
    * @param ns_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/8
    */
    public List<Appeal> getUnWatchedAppealsByNs_id(String ns_id,boolean is_first);

    /**
    * getWatchedAppealsByStu_id
    * <p>学生查询已受理的所有举报（包括已得出结果和未得出结果的。）</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getWatchedAppealsByStu_id(String user_id,boolean is_first);

    /**
    * getNoResultAppealsByStu_id
    * <p>学生查询已受理，但未得出结果的举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getNoResultAppealsByStu_id(String user_id,boolean is_first);

    /**
    * getHasResultAppealsByStu_id
    * <p>学生查询已经得出结果的所有举报</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getHasResultAppealsByStu_id(String user_id,boolean is_first);

    /**
    * getRe_appealedAppealsByStu_id
    * <p>学生查询所有进行了二次举报的举报，即查询结果为初次举报的信息。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getRe_appealedAppealsByStu_id(String user_id,boolean is_first);

    /**
    * getWatchedAppealsByNs_id
    * <p>非学生用户查询所在单位已受理的所有举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getWatchedAppealsByNs_id(String user_id,boolean is_first);

    /**
    * getNoResultAppealsByNs_id
    * <p>非学生用户查询所在单位已受理，但未得出结果的举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getNoResultAppealsByNs_id(String user_id,boolean is_first);

    /**
    * getHasResultAppealsByNs_id
    * <p>非学生用户查询所在单位已给出调查结果的所有举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> getHasResultAppealsByNs_id(String user_id,boolean is_first);

    /**
    * searchForAppealsByKeywords
    * <p>根据关键词查询举报。</p>
    * @param user_id java.lang.String.
     * @param keys java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Appeal>
    * @author hesheng
    * @create: 2021/3/10
    */
    List<Appeal> searchForAppealsByKeywords(String user_id, String keys,boolean is_first);

    Appeal getAppealByRh_id(String rh_id);

    List<Appeal> getAppealsWaitForAuditing(String user_id,boolean is_first);

    List<Appeal> getAppealsHasAudited(String user_id,boolean is_first);

    List<Appeal> getAppealsWaitForComment(String user_id,boolean is_first);

    List<Appeal> getAppealsHasCommented(String user_id,boolean is_first);

    List<Appeal> getFirstAppealsByStu_id(String user_id);

    List<Appeal> getFirstAppealByNs_id(String user_id);

    Appeal getAppealByRl_id(String al_id);


    /**
    * uploadFiles
    * <p>根据alid 添加文件路径</p>
    * @param files java.lang.String.
     * @param alid java.lang.String.
    * @return void
    * @author liubocai
    * @create: 2021-04-04
    */
    void uploadFiles(String files, String alid);

    /**
    * getAllFilesByid
    * <p>根据t_appeal_id获取其所有可下载的文件</p>
    * @param alid java.lang.String.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-04-04
    */
    String getAllFilesByid(String alid);
}
