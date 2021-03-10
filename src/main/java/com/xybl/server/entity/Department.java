package com.xybl.server.entity;

/**
 * Department
 * <p></p>
 *
 * @author liubocai
 * @create 2021-03-01
 **/
public class Department {
    private String id;
    private String name;
    private String address;
    private String web;
    private String level;

    public Department(String id){
        this.id = id;
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(String id, String name, String address, String web, String level) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.web = web;
        this.level = level;
    }

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", web='" + web + '\'' +
                ", level='" + level + '\'' +
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
