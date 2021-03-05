package com.xybl.server.controller;

import com.xybl.server.entity.Appeal;
import com.xybl.server.service.AppealService;
import com.xybl.server.service.LogService;
import com.xybl.server.utils.DatetimeUtil;
import com.xybl.server.utils.ResponseUtil;
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
@RestController
@RequestMapping("/appeal")
public class AppealController {
    @Resource
    private AppealService appealService;
    @Resource
    private LogService logService;

    // http://localhost:8080/server/appeal/addone?user_id=1614600624790001&al_address=湖北省，武汉市，洪山区 武汉大学&al_pos=114.365818,30.534872&al_title=（controller）添加举报测试&al_detail=（controller）添加举报测试&handler=1612715026000001
    @RequestMapping("/addone")
    public Map<String,Object> addOneAppeal(@RequestParam(name = "user_id")String appellant,
                                           @RequestParam(name = "al_address")String al_address,
                                           @RequestParam(name="al_pos")String al_pos,
                                           @RequestParam(name = "al_title")String al_title,
                                           @RequestParam(name="al_detail")String al_detail,
                                           @RequestParam(name = "handler")String handler){
        Appeal appeal=new Appeal();
        String al_id=appealService.genAppealId();
        appeal.setId(al_id);
        appeal.setAl_time(DatetimeUtil.getAndFormatDatetime());
        appeal.setAppellant(appellant);
        appeal.setAddress(al_address);
        appeal.setPos(al_pos);
        appeal.setTitle(al_title);
        appeal.setDetail(al_detail);
//        System.out.println(appeal);
        try{
            appealService.addOneAppeal(appeal, handler);
            logService.addOneLog(appellant,"add one appeal(id="+al_id+")","succeed");
            return response(200,"ok",appeal);
        }catch (Exception e){
            logService.addOneLog(appellant,"add one appeal(id="+al_id+")","failed");
            return response(500,"server error");
        }
    }

    // http://localhost:8080/server/appeal/getbyid?al_id=1614958390011004&user_id=1614600624790001
    @RequestMapping("/getbyid")
    public Map<String,Object> getOneAppealById(@RequestParam(name = "al_id")String al_id,
                                               @RequestParam(name = "user_id")String user_id){
        try{
            Appeal appeal= appealService.getOneAppealById(al_id);
            logService.addOneLog(user_id,"ask for an apeal(id="+al_id+")","succeed");
            return response(200,"ok",appeal);
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog(user_id,"ask for an apeal(id="+al_id+")","failed");
            return response(500,"server error");
        }
    }

    // http://localhost:8080/server/appeal/undermanage?user_id=1612715026000001
    @RequestMapping("/undermanage")
    public Map<String,Object> getAppealsUnderManagement(@RequestParam(name = "user_id")String user_id){
        try {
            List<Appeal> appeals=appealService.getAppealsUnderManagement(user_id);
            logService.addOneLog(user_id, "ask for appeals under management","succeed");
            return response(200,"ok",appeals);
        }catch (Exception e){
            e.printStackTrace();
            logService.addOneLog(user_id,"ask for appeals user management","failed");
            return response(500,"server error");
        }
    }
}
