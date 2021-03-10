package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Message;
import com.xybl.server.utils.IDUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class MessageDaoTest {
    private String user_id;
    @Resource
    private MessageDao messageDao;

    @BeforeEach
    void setUp() {
        user_id="1615105454044009";
    }

    @Test
    void getLastId() {
        Map<String,Object> last_id=messageDao.getLastId();
        System.out.println(last_id);
    }

    @Test
//    @Ignore
    void addOneMessage() {
//        for (int i = 0; i < 3; i++) {
//            Message message=new Message(System.currentTimeMillis()+"00"+i,user_id,"00"+i+":your appeal has been accepted!", false);
//            messageDao.addOneMessage(message);
//        }
    }

    @Test
    void getUnseenMessageByUser_id() {
        List<Message> messages=messageDao.getUnseenMessageByUser_id(user_id);
        for(Message message:messages){
            System.out.println(message);
        }
    }

    @Test
    void getAllMessagesByUser_id() {
        List<Message> messages=messageDao.getAllMessagesByUser_id(user_id);
        for(Message message:messages){
            System.out.println(message);
        }
    }

    @Test
    @Ignore
    void updateOneMessage() {
//        messageDao.updateOneMessage(new Message("1615384235493000",true));
    }
}