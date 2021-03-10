package com.xybl.server.dao;

import com.xybl.server.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    /**
    * getLastId
    * <p>获取当天上一条message的id</p>
    * @param  .
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/10
    */
    public Map<String,Object> getLastId();

    /**
    * addOneMessage
    * <p>插入一条message。</p>
    * @param message com.xybl.server.entity.Message.
    * @return void
    * @author hesheng
    * @create: 2021/3/10
    */
    public void addOneMessage(Message message);

    /**
    * getUnseenMessageByUser_id
    * <p>根据user_id获取未查看的message。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Message>
    * @author hesheng
    * @create: 2021/3/10
    */
    public List<Message> getUnseenMessageByUser_id(String user_id);

    /**
    * getAllMessagesByUser_id
    * <p>根据user_id获取所有的message。</p>
    * @param user_id java.lang.String.
    * @return java.util.List<com.xybl.server.entity.Message>
    * @author hesheng
    * @create: 2021/3/10
    */
    public List<Message> getAllMessagesByUser_id(String user_id);

    /**
    * updateOneMessage
    * <p>修改指定message。</p>
    * @param message com.xybl.server.entity.Message.
    * @return void
    * @author hesheng
    * @create: 2021/3/10
    */
    public void updateOneMessage(Message message);
}
