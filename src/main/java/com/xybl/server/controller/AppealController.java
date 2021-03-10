package com.xybl.server.controller;

import com.xybl.server.entity.Appeal;
import com.xybl.server.service.AppealService;
import com.xybl.server.service.LogService;
import com.xybl.server.service.MessageService;
import com.xybl.server.utils.DatetimeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * AppealController
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/5
 **/
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appeal")
public class AppealController {
    @Resource
    private AppealService appealService;
    @Resource
    private LogService logService;
    @Resource
    private MessageService messageService;

    /**
     * addOneAppeal
     * <p>学生提交一条Appeal。</p>
     *
     * @param appellant  java.lang.String.
     * @param al_address java.lang.String.
     * @param al_pos     java.lang.String.
     * @param al_title   java.lang.String.
     * @param al_detail  java.lang.String.
     * @param handler    java.lang.String.
     * @param last_al    java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/addone?user_id=&al_address=&al_pos=&al_title=&al_detail=&handler=
    @RequestMapping("/addone")
    public Map<String, Object> addOneAppeal(@RequestParam(name = "user_id") String appellant,
                                            @RequestParam(name = "al_address") String al_address,
                                            @RequestParam(name = "al_pos") String al_pos,
                                            @RequestParam(name = "al_title") String al_title,
                                            @RequestParam(name = "al_detail") String al_detail,
                                            @RequestParam(name = "handler") String handler,
                                            @RequestParam(name = "last_al", defaultValue = "not_provided") String last_al) {
        // 初始化参数
        String al_id = appealService.genAppealId();
        Appeal appeal = new Appeal(al_id, DatetimeUtil.getAndFormatDatetime(), appellant, al_address, al_pos, al_title, al_detail);
        try {
            // 插入新的的Appeal
            appealService.addOneAppeal(appeal, handler);
            // 向handler发送提示信息
            messageService.sendMessage(handler,"A student has submitted a new appeal(id="+al_id+").");
            // 计入日志
            logService.addOneLog(appellant, "add one appeal(id=" + al_id + ")", "succeed");

            // 二次申请，更新上一条Appeal的re_appeal.
            if (!"not_provided".equals(last_al)) {
                appealService.updateOneAppealById(new Appeal(last_al, appellant, al_id));
                logService.addOneLog(appellant, "reappeal on appeal(id=" + last_al + ")", "ok");
            }

            return response(200, "ok", appeal);
        } catch (Exception e) {
            e.printStackTrace();
            appealService.deleteOneAppealById(appellant, al_id);
            logService.addOneLog(appellant, "add one appeal(id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    /**
     * deleteOneAppealById
     * <p>学生删除一条举报</p>
     *
     * @param user_id java.lang.String.
     * @param al_id   java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/deletebyid?al_id=&user_id=
    @RequestMapping("/deletebyid")
    public Map<String, Object> deleteOneAppealById(@RequestParam(name = "user_id") String user_id,
                                                   @RequestParam(name = "al_id") String al_id) {
        try {
            appealService.deleteOneAppealById(user_id, al_id);
            logService.addOneLog(user_id, "delete one appeal", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "delete one appeal", "failed");
            return response(500, "server error");
        }
    }

    /**
     * updateOneAppealById
     * <p>学生更新指定appeal的部分信息</p>
     *
     * @param user_id    java.lang.String.
     * @param al_id      java.lang.String.
     * @param al_address java.lang.String.
     * @param al_pos     java.lang.String.
     * @param al_title   java.lang.String.
     * @param al_detail  java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/updatebyid?user_id=&al_id=&al_address=&al_pos=&al_title=&al_detail=&
    @RequestMapping("/updatebyid")
    public Map<String, Object> updateOneAppealById(@RequestParam(name = "user_id") String user_id,
                                                   @RequestParam(name = "al_id") String al_id,
                                                   @RequestParam(name = "al_address", defaultValue = "not_provided") String al_address,
                                                   @RequestParam(name = "al_pos", defaultValue = "not_provided") String al_pos,
                                                   @RequestParam(name = "al_title", defaultValue = "not_provided") String al_title,
                                                   @RequestParam(name = "al_detail", defaultValue = "not_provided") String al_detail) {
        Appeal appeal = new Appeal(al_id, user_id);
        if (!"not_provided".equals(al_address)) {
            appeal.setAddress(al_address);
        }
        if (!"not_provided".equals(al_pos)) {
            appeal.setPos(al_pos);
        }
        if (!"not_provided".equals(al_title)) {
            appeal.setTitle(al_title);
        }
        if (!"not_provided".equals(al_detail)) {
            appeal.setDetail(al_detail);
        }
        try {
            appealService.updateOneAppealById(appeal);
            logService.addOneLog(user_id, "update one appeal(id=" + al_id + ")", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "update one appeal(id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getOneAppealById
     * <p>用户根据Appeal id查询appeal详情。</p>
     *
     * @param al_id   java.lang.String.
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/getbyid?al_id=&user_id=
    @RequestMapping("/getbyid")
    public Map<String, Object> getOneAppealById(@RequestParam(name = "al_id") String al_id,
                                                @RequestParam(name = "user_id") String user_id) {
        try {
            Appeal appeal = appealService.getOneAppealById(al_id);
            logService.addOneLog(user_id, "ask for an apeal(id=" + al_id + ")", "succeed");
            return response(200, "ok", appeal);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for an apeal(id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getAppealsByUser_id
     * <p>学生查询自己提交的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/getbyuserid?user_id=
    @RequestMapping("/get_bs")
    public Map<String, Object> getAppealsByUser_id(@RequestParam(name = "user_id") String user_id,
                                                   @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getAppealsByUserId(user_id, is_first!=0);
            logService.addOneLog(user_id, "ask for user's all appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for user's all appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getUnwatchedAppealsByUser_id
     * <p>学生查询自己的所有未受理的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_unwatched_bs")
    public Map<String, Object> getUnwatchedAppealsByUser_id(@RequestParam(name = "user_id") String user_id,
                                                            @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getUnWatchedAppealsByStu_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for unwatched appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for unwatched appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getWatchedAppealsByStu_id
     * <p>学生查询已受理的所有举报（包括已得出结果和未得出结果的。）</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_watched_bs")
    public Map<String, Object> getWatchedAppealsByStu_id(@RequestParam(name = "user_id") String user_id,
                                                         @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getWatchedAppealsByStu_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for watched appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for watched appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getNoResultAppealsByStu_id
     * <p>学生查询已受理，但未得出结果的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_noresult_bs")
    public Map<String, Object> getNoResultAppealsByStu_id(@RequestParam(name = "user_id") String user_id,
                                                          @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getNoResultAppealsByStu_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for no result appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for no result appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getHasResultAppealsByStu_id
     * <p>学生查询已经得出结果的所有举报</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_hasresult_bs")
    public Map<String, Object> getHasResultAppealsByStu_id(@RequestParam(name = "user_id") String user_id,
                                                           @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getHasResultAppealsByStu_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for has result appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for has result appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getRe_appealedAppealsByStu_id
     * <p>学生查询所有进行了二次举报的举报，即查询结果为初次举报的信息。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_real")
    public Map<String, Object> getRe_appealedAppealsByStu_id(@RequestParam(name = "user_id") String user_id,
                                                             @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getRe_appealedAppealsByStu_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for re_appealed appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for re_appealed appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getAppealsUnderManagement
     * <p>非学生用户查询所有提交到所在单位的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    // http://localhost:8080/server/appeal/undermanage?user_id=
    @RequestMapping("/undermanage")
    public Map<String, Object> getAppealsUnderManagement(@RequestParam(name = "user_id") String user_id,
                                                         @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getAppealsUnderManagement(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for appeals under management", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for appeals under management", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getUnwatchedAppealsByNs_id
     * <p>非学生用户查询所在单位未受理的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_unwatched_ns")
    public Map<String, Object> getUnwatchedAppealsByNs_id(@RequestParam(name = "user_id") String user_id,
                                                          @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getUnWatchedAppealsByNs_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for unwatched appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for unwatched appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getWatchedAppealsByNs_id
     * <p>非学生用户查询所在单位已受理的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_watched_ns")
    public Map<String, Object> getWatchedAppealsByNs_id(@RequestParam(name = "user_id") String user_id,
                                                        @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getWatchedAppealsByNs_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for watched appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for watched appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getNoResultAppealsByNs_id
     * <p>非学生用户查询所在单位已受理，但未得出结果的举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_noresult_ns")
    public Map<String, Object> getNoResultAppealsByNs_id(@RequestParam(name = "user_id") String user_id,
                                                         @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getNoResultAppealsByNs_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for no result appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for no result appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
     * getHasResultAppealsByNs_id
     * <p>非学生用户查询所在单位已给出调查结果的所有举报。</p>
     *
     * @param user_id java.lang.String.
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author hesheng
     * @create: 2021/3/10
     */
    @RequestMapping("/get_hasresult_ns")
    public Map<String, Object> getHasResultAppealsByNs_id(@RequestParam(name = "user_id") String user_id,
                                                          @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.getHasResultAppealsByNs_id(user_id,is_first!=0);
            logService.addOneLog(user_id, "ask for has result appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for has result appeals", "failed");
            return response(500, "server error");
        }
    }

    /**
    * searchForAppealsByKeywords
    * <p>根据关键词查询举报。</p>
    * @param user_id java.lang.String.
     * @param keys java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/10
    */
    @RequestMapping("/search_al")
    public Map<String, Object> searchForAppealsByKeywords(@RequestParam(name = "user_id") String user_id,
                                                          @RequestParam(name = "keys") String keys,
                                                          @RequestParam(name = "is_first",defaultValue = "0")int is_first) {
        try {
            List<Appeal> appeals = appealService.searchForAppealsByKeywords(user_id,keys,is_first!=0);
            logService.addOneLog(user_id, "search for appeals by keywords", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "search for appeals by keywords", "failed");
            return response(500, "server error");
        }
    }

    /**
    * getAppealsWaitForAuditing
    * <p>获取待审核的举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/10
    */
    @RequestMapping("/wait_audit")
    public Map<String,Object> getAppealsWaitForAuditing(@RequestParam(name = "user_id")String user_id,
                                                        @RequestParam(name = "is_first",defaultValue = "0")int is_first){
        try {
            List<Appeal> appeals = appealService.getAppealsWaitForAuditing(user_id,is_first!=0);
            logService.addOneLog(user_id, "get appeals waiting for auditing", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get appeals waiting for auditing", "failed");
            return response(500, "server error");
        }
    }

    /**
    * getAppealsHasAudited
    * <p>获取已经审核过的举报。</p>
    * @param user_id java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/10
    */
    @RequestMapping("/get_audited")
    public Map<String,Object> getAppealsHasAudited(@RequestParam(name = "user_id")String user_id,
                                                   @RequestParam(name = "is_first",defaultValue = "0")int is_first){
        try {
            List<Appeal> appeals = appealService.getAppealsHasAudited(user_id,is_first!=0);
            logService.addOneLog(user_id, "get appeals that already been audited", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get appeals that already been audited", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/wait_comment")
    public Map<String,Object> getAppealsWaitForComment(@RequestParam(name = "user_id")String user_id,
                                                       @RequestParam(name = "is_first",defaultValue = "0")int is_first){
        try {
            List<Appeal> appeals = appealService.getAppealsWaitForComment(user_id,is_first!=0);
            logService.addOneLog(user_id, "get appeals that already been audited", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get appeals that already been audited", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/get_commented")
    public Map<String,Object> getAppealsHasCommented(@RequestParam(name = "user_id")String user_id,
                                                     @RequestParam(name = "is_first",defaultValue = "0")int is_first){
        try {
            List<Appeal> appeals = appealService.getAppealsHasCommented(user_id,is_first!=0);
            logService.addOneLog(user_id, "get appeals that already been audited", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get appeals that already been audited", "failed");
            return response(500, "server error");
        }
    }
}
