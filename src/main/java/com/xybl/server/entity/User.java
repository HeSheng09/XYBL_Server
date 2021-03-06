package com.xybl.server.entity;

/**
 * User
 * <p>实体类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class User {
    public User(String id, String name, boolean role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User(String id, String name, String pwd, boolean role) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.role = role;
    }

    public User() {
    }

    private String id;
    private String name;
    private String pwd;
    private boolean role; //false 代表student，true 代表user。故此属性默认值为0,即学生。

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role=" + role +
                '}';
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
