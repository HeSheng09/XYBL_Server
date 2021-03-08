package com.xybl.server.dao;

import com.xybl.server.entity.Appeal;
import com.xybl.server.entity.Research;

import java.util.List;
import java.util.Map;

/**
 * ResearchDao
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
public interface ResearchDao {
    /**
     * getOneResearchById
     * <p>按ID查询一条research。</p>
     *
     * @param id java.lang.String.
     * @return com.xybl.server.entity.Research
     * @author hesheng
     * @create: 2021/3/1
     */
    public Research getOneResearchById(String id);

    /**
     * getResearches
     * <p>查询多条Research（最多100条）。</p>
     *
     * @param .
     * @return java.util.List<com.xybl.server.entity.Research>
     * @author hesheng
     * @create: 2021/3/1
     */
    public List<Research> getResearches();

    /**
     * addOneResearch
     * <p>添加一条Research。</p>
     *
     * @param research com.xybl.server.entity.Research.
     * @return void
     * @author hesheng
     * @create: 2021/3/1
     */
    public void addOneResearch(Research research);

    /**
     * deleteOneResearchById
     * <p>根据id删除一条Research。同组织且权限不为3才可以删除。</p>
     *
     * @param id      java.lang.String.
     * @param user_id java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/7
     */
    public void deleteOneResearchById(String id, String user_id);

    /**
     * updateOneResearch
     * <p>根据ID修改指定Research内容。</p>
     *
     * @param research com.xybl.server.entity.Research.
     * @return void
     * @author hesheng
     * @create: 2021/3/1
     */
    public void selfUpdateOneResearch(Research research);

    public void superUpdateOneResearch(Research research);

    public void stuUpdateOneResearch(String stu_id, String rh_id, String app_comment);

    /**
     * getLastId
     * <p>查找上一条Research ID。</p>
     *
     * @param .
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/1
     */
    public Map<String, Object> getLastId();

    /**
     * addRelAlRh
     * <p>添加一条关系至r_al_rh。</p>
     *
     * @param rh_id java.lang.String.
     * @param al_id java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/7
     */
    public void addRelAlRh(String rh_id, String al_id);

    /**
     * deleteRelAlRh
     * <p>删除r_al_rh中一条关系。注意：同机构且权限不为3才可以删除。</p>
     *
     * @param rh_id   java.lang.String.
     * @param user_id java.lang.String.
     * @return void
     * @author hesheng
     * @create: 2021/3/7
     */
    public void deleteRelAlRh(String rh_id, String user_id);

    /**
    * getOneResearchByAlId
    * <p>根据appeal id获取其对应的research详情。</p>
    * @param al_id java.lang.String.
    * @return com.xybl.server.entity.Research
    * @author hesheng
    * @create: 2021/3/8
    */
    public Research getOneResearchByAlId(String al_id);
}
