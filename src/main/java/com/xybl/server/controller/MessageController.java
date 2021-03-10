package com.xybl.server.controller;

import com.xybl.server.entity.Message;
import com.xybl.server.service.LogService;
import com.xybl.server.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * MessageController
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/10
 **/
@Controller
@RequestMapping("/msg")
public class MessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private LogService logService;

    @RequestMapping("/all")
    public Map<String,Object> getAllMessagesByUser_id(@RequestParam(name = "user_id")String user_id){
        try {
            List<Message> messages = messageService.getAllMessagesByUser_id(user_id);
            logService.addOneLog(user_id, "get all messages", "succeed");
            return response(200, "ok", messages);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get all messages", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/unseen")
    public Map<String,Object> getUnseenMessagesByUser_id(@RequestParam(name = "user_id")String user_id){
        try {
            List<Message> messages = messageService.getUnseenMessagesByUser_id(user_id);
            logService.addOneLog(user_id, "get unseen messages", "succeed");
            return response(200, "ok", messages);
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "get unseen messages", "failed");
            return response(500, "server error");
        }
    }

    @RequestMapping("/change_flag")
    public Map<String,Object> changeMessageFlag(@RequestParam(name = "user_id")String user_id,
                                                @RequestParam(name = "msg_id")String msg_id,
                                                @RequestParam(name = "flag",defaultValue = "0")int flag){
        try {
            messageService.changeMessageFlag(msg_id,flag!=0);
            logService.addOneLog(user_id, "change message flag to "+(flag!=0), "succeed");
            return response(200, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            logService.addOneLog(user_id, "change message flag to "+(flag!=0), "failed");
            return response(500, "server error");
        }
    }
}
