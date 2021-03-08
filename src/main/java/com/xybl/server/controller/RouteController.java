package com.xybl.server.controller;

import com.xybl.server.service.LogService;
import com.xybl.server.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    // 通过al_id获取一条完整的处理流程
    @RequestMapping("/get_complete")
    public Map<String,Object> getOneCompleteRoute(@RequestParam("user_id") String user_id,
                                                  @RequestParam("al_id")String al_id){
        try {
            Map<String,Object> route=routeService.getOneCompleteRoute(al_id);
            logService.addOneLog(user_id,"ask for a complete route(al_id="+al_id+")","succeed");
            return response(200,"ok",route);
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog(user_id,"ask for a complete route(al_id="+al_id+")","failed");
            return response(500,"server error");
        }
    }
}
