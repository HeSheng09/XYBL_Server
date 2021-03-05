package com.xybl.server.entity;

/**
 * Log
 * <p>实体类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class Log {
    private String id;
//    private String log_time;
    private String detail;

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Log() {
    }

    public Log(String id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
