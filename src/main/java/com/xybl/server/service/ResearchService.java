package com.xybl.server.service;

import com.xybl.server.entity.Research;

/**
 * ResearchService
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/5
 **/
public interface ResearchService {
    /**
     * genResearchId
     * <p>生成一条research id。</p>
     *
     * @param .
     * @return java.lang.String
     * @author hesheng
     * @create: 2021/3/5
     */
    public String genResearchId();

    /**
    * addOneResearch
    * <p>提交一条Research。</p>
    * @param research com.xybl.server.entity.Research.
     * @param al_id java.lang.String.
    * @return boolean 插入成功，true；否则，false。
    * @author hesheng
    * @create: 2021/3/7
    */
    public boolean addOneResearch(Research research, String al_id);

    /**
    * deleteOneResearch
    * <p>从数据库中删除一条Research。</p>
    * @param research com.xybl.server.entity.Research.
     * @param al_id java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/7
    */
    public void deleteOneResearch(String rh_id,String user_id);

    /**
    * selfUpdateReseach
    * <p>受理举报的机构更新调查内容。可更新的字段包括：re_research, result, res_time, auditor。</p>
    * @param research com.xybl.server.entity.Research.
    * @return void
    * @author hesheng
    * @create: 2021/3/7
    */
    public void selfUpdateResearch(Research research);

    /**
    * superUpdateResearch
    * <p>负责审核的机构审核后更新调查结果内容。可更新的字段包括：auditor, au_res, au_time。</p>
    * @param research com.xybl.server.entity.Research.
    * @return void
    * @author hesheng
    * @create: 2021/3/7
    */
    public void superUpdateResearch(Research research);

    /**
    * stuUpdateResearch
    * <p>用户更新对于处理的意见。仅可更新app_comment字段。</p>
    * @param stu_id java.lang.String.
     * @param rh_id java.lang.String.
     * @param app_comment java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/7
    */
    public void stuUpdateResearch(String stu_id,String rh_id,String app_comment);

    /**
    * getOneResearchById
    * <p>按id查询一条Research。</p>
    * @param rh_id java.lang.String.
    * @return com.xybl.server.entity.Research
    * @author hesheng
    * @create: 2021/3/7
    */
    public Research getOneResearchById(String rh_id);

    /**
    * getOneResearchByAl_id
    * <p>按appeal id查询其对应的research。</p>
    * @param al_id java.lang.String.
    * @return com.xybl.server.entity.Research
    * @author hesheng
    * @create: 2021/3/10
    */
    Research getOneResearchByAl_id(String al_id);

    /**
     * uploadFiles
     * <p>根据research id添加文件</p>
     * @param files java.lang.String.
     * @param rhid java.lang.String.
     * @return void
     * @author liubocai
     * @create: 2021-04-04
     */
    void uploadFiles(String files, String rhid);

    /**
     * getAllFilesByid
     * <p>根据t_research_id获取其所有可下载的文件名</p>
     * @param rhid java.lang.String.
     * @return java.lang.String
     * @author liubocai
     * @create: 2021-04-04
     */
    String getAllFilesByid(String rhid);
}
