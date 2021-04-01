package com.xybl.server.controller;

import com.xybl.server.entity.Appeal;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Research;
import com.xybl.server.service.*;
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
    @Resource
    private MessageService messageService;
    @Resource
    private AppealService appealService;

    // http://localhost:8080/server/research/addone?user_id=&al_id=&last_rh=
    @RequestMapping("/addone")
    public Map<String, Object> addOneResearch(@RequestParam(name = "user_id") String user_id,
                                              @RequestParam(name = "al_id") String al_id,
                                              @RequestParam(name = "last_rh", defaultValue = "not_provided") String last_rh) {
        try {
            NsUser user = userService.getNsUserById(user_id);
            if (Integer.parseInt(user.getPrivilege().substring(1, 2)) < 3) {
                // 查询是否已有处理
                Research is_rh = researchService.getOneResearchByAl_id(al_id);
                if (is_rh != null) {
                    logService.addOneLog(user_id, "add one research(id=(" + is_rh.getId() + ")", "failed");
                    return response(602, "already exist", is_rh);
                }

                // privilege=0,1,2
                String rh_id = researchService.genResearchId();
                Research research = new Research(rh_id, user_id, getAndFormatDatetime());
                if (researchService.addOneResearch(research, al_id)) {

                    messageService.sendMessage(appealService.getOneAppealById(al_id).getAppellant(), "Your appeal(id=" + al_id + ") has been accepted!");
                    // 插入成功
                    logService.addOneLog(user_id, "add one research(id=(" + rh_id + ")", "succeed");

                    // 是否二次处理。若是，则更新上次举报的re_research属性
                    if (!"not_provided".equals(last_rh)) {
                        Research lastRh = researchService.getOneResearchById(last_rh);
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
    public Map<String, Object> deleteOneResearch(@RequestParam(name = "user_id") String user_id,
                                                 @RequestParam(name = "rh_id") String rh_id) {
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
    public Map<String, Object> submitResearchResult(@RequestParam(name = "user_id") String user_id,
                                                    @RequestParam(name = "rh_id") String rh_id,
                                                    @RequestParam(name = "result") String result) {
        Research research = new Research(rh_id, user_id);
        research.setResult(result);
        research.setRes_time(getAndFormatDatetime());
        try {
            // 提交完结果之后，需要将此research提交给上级部门审核。故需要update rh_auditor字段
            String super_id = userService.getSuperDmId(user_id);
            research.setAuditor(super_id);

            researchService.selfUpdateResearch(research);

            // 通知学生已经提交调查结果
            Appeal appeal = appealService.getAppealByRh_id(rh_id);
            messageService.sendMessage(appeal.getAppellant(), "The organization has submitted research result on your appeal(id=" + appeal.getId() + ")!");

            // 通知上级部门进行审核
            messageService.sendMessage(super_id, "A new research on appeal(id=" + appeal.getId() + ") need to be examined.");

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
    public Map<String, Object> superAuditResearch(@RequestParam(name = "user_id") String user_id,
                                                  @RequestParam(name = "rh_id") String rh_id,
                                                  @RequestParam(name = "rh_aures") String rh_aures) {
        Research research = new Research();
        research.setId(rh_id);
        research.setAuditor(user_id);
        research.setAu_result(rh_aures);
        research.setAu_time(getAndFormatDatetime());
        try {
            researchService.superUpdateResearch(research);

            // 通知处理部门已经审核
            String handler = researchService.getOneResearchById(rh_id).getHandler();
            Appeal appeal = appealService.getAppealByRh_id(rh_id);
            messageService.sendMessage(handler, "The research on appeal(id=" + appeal.getId() + ") has been examined!");
            // 通知用户已经审核结束
            messageService.sendMessage(appeal.getAppellant(), "Your appeal(id=" + appeal.getId() + ") has been examined.");

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
    public Map<String, Object> updateOneResearch(@RequestParam(name = "user_id") String user_id,
                                                 @RequestParam(name = "rh_id") String rh_id,
                                                 @RequestParam(name = "app_comment") String app_comment) {
        try {
            researchService.stuUpdateResearch(user_id, rh_id, app_comment);

            Research research = researchService.getOneResearchById(rh_id);
            Appeal appeal = appealService.getAppealByRh_id(rh_id);
            // 通知管理和审核部门用户意见
            messageService.sendMessage(research.getHandler(), "The student has submitted comment on your research about appeal(id=" + appeal.getAppellant() + ").");
            messageService.sendMessage(research.getAuditor(), "The student has submitted comment on your research about appeal(id=" + appeal.getAppellant() + ").");

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
    public Map<String, Object> getOneResearch(@RequestParam(name = "user_id") String user_id,
                                              @RequestParam(name = "rh_id") String rh_id) {
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

    @RequestMapping("/get_by_al_id")
    public Map<String, Object> getOneResearchByAl_id(@RequestParam(name = "user_id") String user_id,
                                                     @RequestParam(name = "al_id") String al_id) {
        try {
            Research research = researchService.getOneResearchByAl_id(al_id);
            logService.addOneLog(user_id, "ask for one research by al_id(=" + al_id + ")", "succeed");
            return response(200, "ok", research);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for one research by al_id(=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }
}
