package com.xybl.server.entity;

/**
 * Message
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/10
 **/
public class Message {
    private String id;
    private String user_id;
    private String msg;
    private boolean flag;

    public Message() {
    }

    public Message(String id) {
        this.id = id;
    }

    public Message(String id, boolean flag) {
        this.id = id;
        this.flag = flag;
    }

    public Message(String id, String user_id, String msg) {
        this.id = id;
        this.user_id = user_id;
        this.msg = msg;
    }

    public Message(String id, String user_id, String msg, boolean flag) {
        this.id = id;
        this.user_id = user_id;
        this.msg = msg;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", msg='" + msg + '\'' +
                ", flag=" + flag +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
