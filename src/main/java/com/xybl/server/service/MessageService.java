package com.xybl.server.service;

import com.xybl.server.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> getAllMessagesByUser_id(String user_id);

    public List<Message> getUnseenMessagesByUser_id(String user_id);

    public void sendMessage(String target, String msg);

    public String genMessageId();

    public void changeMessageFlag(String msg_id, boolean flag);
}
