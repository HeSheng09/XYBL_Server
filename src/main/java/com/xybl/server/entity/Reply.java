package com.xybl.server.entity;

/**
 * Reply
 * <p>实体类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class Reply {
    private String id;
    private String post_id;
    private String detail;
    private String ry_time;
    private String ry_address;
    private String ry_user;

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", post_id='" + post_id + '\'' +
                ", detail='" + detail + '\'' +
                ", ry_time='" + ry_time + '\'' +
                ", ry_address='" + ry_address + '\'' +
                ", ry_user='" + ry_user + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRy_time() {
        return ry_time;
    }

    public void setRy_time(String ry_time) {
        this.ry_time = ry_time;
    }

    public String getRy_address() {
        return ry_address;
    }

    public void setRy_address(String ry_address) {
        this.ry_address = ry_address;
    }

    public String getRy_user() {
        return ry_user;
    }

    public void setRy_user(String ry_user) {
        this.ry_user = ry_user;
    }
}
