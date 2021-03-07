package com.xybl.server.controller;

import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Research;
import com.xybl.server.service.LogService;
import com.xybl.server.service.ResearchService;
import com.xybl.server.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

import static com.xybl.server.utils.DatetimeUtil.getAndFormatDatetime;
import static com.xybl.server.utils.ResponseUtil.response;

/**
 * ResearchController
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/6
 **/
@RestController
@RequestMapping("/research")
public class ResearchController {
    @Resource
    private ResearchService researchService;
    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    // http://localhost:8080/server/research/addone?user_id=&al_id=&last_rh=
    @RequestMapping("/addone")
    public Map<String, Object> addOneResearch(@RequestParam("user_id") String user_id,
                                              @RequestParam("al_id") String al_id,
                                              @RequestParam(value = "last_rh", defaultValue = "not_provided") String last_rh) {
        try {
            NsUser user = userService.getNsUserById(user_id);
            if (Integer.parseInt(user.getPrivilege().substring(1, 2)) < 3) {
                // privilege=0,1,2
                String rh_id = researchService.genResearchId();
                Research research = new Research(rh_id, user_id, getAndFormatDatetime());
                if (researchService.addOneResearch(research, al_id)) {
                    // 插入成功
                    logService.addOneLog(user_id, "add one research(id=(" + rh_id + ")", "succeed");

                    // 是否二次举报。若是，则更新上次举报的re_appeal属性
                    if (!"not_provided".equals(last_rh)) {
                        Research lastRh=researchService.getOneResearchById(last_rh);
                        lastRh.setRe_research(rh_id);
                        researchService.selfUpdateResearch(lastRh);
                    }

                    return response(200, "ok", research);
                } else {
                    logService.addOneLog(user_id, "add one research(id=(" + rh_id + ")", "failed");
                    return response(501, "unknown server error");
                }
            } else {
                logService.addOneLog(user_id, "add one research", "failed");
                return response(601, "Insufficient authority");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "add one research", "failed");
            return response(500, "server error");
        }
    }

    // http://localhost:8080/server/research/deleteone?user_id=&rh_id=
    @RequestMapping("/deleteone")
    public Map<String, Object> deleteOneResearch(@RequestParam("user_id") String user_id,
                                                 @RequestParam("rh_id") String rh_id) {
        try {
            researchService.deleteOneResearch(rh_id, user_id);
            logService.addOneLog(user_id, "delete one research(id=" + rh_id + ")", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "delete one research(id=" + rh_id + ")", "failed");
            return response(500, "server error");
        }
    }

    // 机构提交处理结果
    // http://localhost:8080/server/research/submit_result?user_id=&rh_id=&result=
    @RequestMapping("/submit_result")
    public Map<String, Object> submitResearchResult(@RequestParam("user_id") String user_id,
                                                    @RequestParam("rh_id") String rh_id,
                                                    @RequestParam("result") String result) {
        Research research = new Research(rh_id, user_id);
        research.setResult(result);
        research.setRes_time(getAndFormatDatetime());
        try {
            // 提交完结果之后，需要将此research提交给上级部门审核。故需要update rh_auditor字段
            research.setAuditor(userService.getSuperDmId(user_id));

            researchService.selfUpdateResearch(research);
            logService.addOneLog(user_id, "submit research(id=" + rh_id + ") result", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "submit research(id=" + rh_id + ") result", "failed");
            return response(500, "server error");
        }
    }

    // 上级机构审核
    // http://localhost:8080/server/research/super_audit?user_id=&rh_id=&rh_aures=
    @RequestMapping("/super_audit")
    public Map<String, Object> superAuditResearch(@RequestParam("user_id") String user_id,
                                                  @RequestParam("rh_id") String rh_id,
                                                  @RequestParam("rh_aures") String rh_aures) {
        Research research = new Research();
        research.setId(rh_id);
        research.setAuditor(user_id);
        research.setAu_result(rh_aures);
        research.setAu_time(getAndFormatDatetime());
        try {
            researchService.superUpdateResearch(research);
            logService.addOneLog(user_id, "examine research(id=" + rh_id + ")", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "examine research(id=" + rh_id + ")", "failed");
            return response(500, "server error");
        }
    }

    // 用户提交对于处理的意见
    // http://localhost:8080/server/research/submit_comment?user_id=&rh_id=&app_comment=
    @RequestMapping("/submit_comment")
    public Map<String, Object> updateOneResearch(@RequestParam("user_id") String user_id,
                                                 @RequestParam("rh_id") String rh_id,
                                                 @RequestParam("app_comment") String app_comment) {
        try {
            researchService.stuUpdateResearch(user_id, rh_id, app_comment);
            logService.addOneLog(user_id, "submit research(id=" + rh_id + ") comment", "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "submit research(id=" + rh_id + ") comment", "failed");
            return response(500, "server error");
        }
    }

    // http://localhost:8080/server/research/getone?user_id=&rh_id=
    @RequestMapping("/getone")
    public Map<String, Object> getOneResearch(@RequestParam("user_id") String user_id,
                                              @RequestParam("rh_id") String rh_id) {
        try {
            Research research = researchService.getOneResearchById(rh_id);
            logService.addOneLog(user_id, "ask for one research(id=" + rh_id + ")", "succeed");
            return response(200, "ok", research);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for one research(id=" + rh_id + ")", "failed");
            return response(500, "server error");
        }
    }
}
