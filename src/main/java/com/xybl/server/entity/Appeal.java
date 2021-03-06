package com.xybl.server.entity;

import com.xybl.server.utils.DatetimeUtil;

/**
 * Appeal
 * <p>实体类。<br>
 * @author hesheng
 * @create 2021/2/2
 **/
public class Appeal {
    private String id;
    private String al_time;
    private String appellant;
    private String address;
    private String pos;
    private String title;
    private String detail;
    private String filepath;
    private String re_appeal;

    public Appeal() {
    }

    public Appeal(String id, String al_time, String appellant, String address, String pos, String title, String detail) {
        this.id = id;
        this.al_time = al_time;
        this.appellant = appellant;
        this.address = address;
        this.pos = pos;
        this.title = title;
        this.detail = detail;
    }

    public Appeal(String id, String appellant) {
        this.id = id;
        this.appellant = appellant;
    }

    public Appeal(String id, String appellant, String re_appeal) {
        this.id = id;
        this.appellant = appellant;
        this.re_appeal = re_appeal;
    }

    @Override
    public String toString() {
        return "Appeal{" +
                "id='" + id + '\'' +
                ", al_time='" + al_time + '\'' +
                ", appellant='" + appellant + '\'' +
                ", address='" + address + '\'' +
                ", pos='" + pos + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", filepath='" + filepath + '\'' +
                ", re_appeal='" + re_appeal + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAl_time() {
        return al_time;
    }

    public void setAl_time(String al_time) {
        this.al_time = al_time;
    }

    public String getAppellant() {
        return appellant;
    }

    public void setAppellant(String appellant) {
        this.appellant = appellant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getRe_appeal() {
        return re_appeal;
    }

    public void setRe_appeal(String re_appeal) {
        this.re_appeal = re_appeal;
    }
}
