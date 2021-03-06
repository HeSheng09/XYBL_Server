package com.xybl.server.controller;

import com.xybl.server.entity.Appeal;
import com.xybl.server.service.AppealService;
import com.xybl.server.service.LogService;
import com.xybl.server.utils.DatetimeUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
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
            // 二次申请，首先更新上一条Appeal的re_appeal.
            if (!"not_provided".equals(last_al)) {
                appealService.updateOneAppealById(new Appeal(last_al, appellant, al_id));
            }
            // 插入新的的Appeal
            appealService.addOneAppeal(appeal, handler);
            logService.addOneLog(appellant, "add one appeal(id=" + al_id + ")", "succeed");
            return response(200, "ok", appeal);
        } catch (Exception e) {
            e.printStackTrace();
            appealService.deleteOneAppealById(appellant, al_id);
            logService.addOneLog(appellant, "add one appeal(id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

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

    // http://localhost:8080/server/appeal/undermanage?user_id=
    @RequestMapping("/undermanage")
    public Map<String, Object> getAppealsUnderManagement(@RequestParam(name = "user_id") String user_id) {
        try {
            List<Appeal> appeals = appealService.getAppealsUnderManagement(user_id);
            logService.addOneLog(user_id, "ask for appeals under management", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for appeals under management", "failed");
            return response(500, "server error");
        }
    }

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

    // http://localhost:8080/server/appeal/getbyuserid?user_id=
    @RequestMapping("/getbyuserid")
    public Map<String, Object> getAppealsByUser_id(@RequestParam(name = "user_id") String user_id) {
        try {
            List<Appeal> appeals = appealService.getAppealsByUserId(user_id);
            logService.addOneLog(user_id, "ask for user's all appeals", "succeed");
            return response(200, "ok", appeals);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for user's all appeals", "failed");
            return response(500, "server error");
        }
    }
}
