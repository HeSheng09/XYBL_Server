package com.xybl.server.entity;

/**
 * School
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
public class School {
    private String id;
    private String name;
    private String address;
    private String postcode;
    private String tel;
    private String web;

    public School(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public School(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", tel='" + tel + '\'' +
                ", web='" + web + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
