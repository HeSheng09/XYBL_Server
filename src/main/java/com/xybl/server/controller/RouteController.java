package com.xybl.server.controller;

import com.xybl.server.entity.Appeal;
import com.xybl.server.service.AppealService;
import com.xybl.server.service.LogService;
import com.xybl.server.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * RouteController
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/8
 **/
@RestController
@RequestMapping("/route")
public class RouteController {
    @Resource
    private RouteService routeService;
    @Resource
    private LogService logService;
    @Resource
    private AppealService appealService;

    // 通过al_id获取一条完整的处理流程
    @RequestMapping("/get_complete")
    public Map<String, Object> getOneCompleteRoute(@RequestParam("user_id") String user_id,
                                                   @RequestParam("al_id") String al_id) {
        try {
            Map<String, Object> route = routeService.getOneCompleteRoute(al_id);
            logService.addOneLog(user_id, "ask for a complete route(al_id=" + al_id + ")", "succeed");
            return response(200, "ok", route);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for a complete route(al_id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/get_cur_route")
    public Map<String, Object> getCurrentRoute(@RequestParam("user_id") String user_id,
                                               @RequestParam("al_id") String al_id) {
        try {
            Map<String, Object> route = routeService.getCurRoute(al_id);
            logService.addOneLog(user_id, "ask for the current route(al_id=" + al_id + ")", "succeed");
            return response(200, "ok", route);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for the current route(al_id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/get_follow_routes")
    public Map<String, Object> getFollowRoutes(@RequestParam("user_id") String user_id,
                                               @RequestParam("al_id") String al_id) {
        try {
            Appeal appeal = appealService.getOneAppealById(al_id);
            if (al_id != null) {
                List<Map<String, Object>> routes = routeService.getFollowRoutes(appeal.getRe_appeal());
                logService.addOneLog(user_id, "ask for follow routes(al_id=" + al_id + ")", "succeed");
                return response(200, "ok", routes);
            } else {
                logService.addOneLog(user_id, "ask for follow routes(al_id=" + al_id + ")", "failed");
                return response(602, "object not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for follow routes(al_id=" + al_id + ")", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/get_before_com")
    public Map<String, Object> getBeforeComRoute(@RequestParam("user_id") String user_id,
                                                 @RequestParam("al_id") String al_id) {
        try {
            Map<String, Object> route = routeService.getBeforeComRoute(al_id);
            logService.addOneLog(user_id, "ask for a complete route(al_id=" + al_id + ") including before appeal and researches","succeed");
            return response(200, "ok", route);
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog(user_id, "ask for a complete route(al_id=" + al_id + ") including before appeal and researches","failed");
            return response(500, "server error");
        }
    }
}
