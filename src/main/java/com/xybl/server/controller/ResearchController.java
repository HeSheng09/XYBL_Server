package com.xybl.server.controller;

import com.xybl.server.service.LogService;
import com.xybl.server.service.ResearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    @RequestMapping("/addone")
    public Map<String,Object> addOneResearch(){
        try {
            logService.addOneLog("","","succeed");
            return response(200,"ok");
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog("","","failed");
            return response(500,"server error");
        }
    }

    @RequestMapping("/deleteone")
    public Map<String,Object> deleteOneResearch(){
        try {
            logService.addOneLog("","","succeed");
            return response(200,"ok");
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog("","","failed");
            return response(500,"server error");
        }
    }

    @RequestMapping("/updateone")
    public Map<String,Object> updateOneResearch(){
        try {
            logService.addOneLog("","","succeed");
            return response(200,"ok");
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog("","","failed");
            return response(500,"server error");
        }
    }

    @RequestMapping("/getone")
    public Map<String ,Object> getOneResearch(){
        try {
            logService.addOneLog("","","succeed");
            return response(200,"ok");
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog("","","failed");
            return response(500,"server error");
        }
    }
}
