package com.xybl.server.entity;

public class Post {
    private String id;
    private String post_time;
    private String post_address;
    private String post_abstract;
    private String detail;
    private String post_user;

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", post_time='" + post_time + '\'' +
                ", post_address='" + post_address + '\'' +
                ", post_abstract='" + post_abstract + '\'' +
                ", detail='" + detail + '\'' +
                ", post_user='" + post_user + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getPost_address() {
        return post_address;
    }

    public void setPost_address(String post_address) {
        this.post_address = post_address;
    }

    public String getPost_abstract() {
        return post_abstract;
    }

    public void setPost_abstract(String post_abstract) {
        this.post_abstract = post_abstract;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPost_user() {
        return post_user;
    }

    public void setPost_user(String post_user) {
        this.post_user = post_user;
    }
}
