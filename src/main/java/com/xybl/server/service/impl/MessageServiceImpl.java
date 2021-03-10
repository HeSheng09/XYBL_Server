package com.xybl.server.service.impl;

import com.xybl.server.dao.MessageDao;
import com.xybl.server.entity.Message;
import com.xybl.server.service.MessageService;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * MessageServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/10
 **/
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Override
    public List<Message> getAllMessagesByUser_id(String user_id) {
        return messageDao.getAllMessagesByUser_id(user_id);
    }

    @Override
    public List<Message> getUnseenMessagesByUser_id(String user_id) {
        return messageDao.getUnseenMessageByUser_id(user_id);
    }

    @Override
    public void sendMessage(String target, String msg) {
        messageDao.addOneMessage(new Message(genMessageId(), target, msg, false));
    }

    @Override
    public String genMessageId() {
        Map<String, Object> map = messageDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }

    @Override
    public void changeMessageFlag(String msg_id, boolean flag) {
        messageDao.updateOneMessage(new Message(msg_id, flag));
    }
}
