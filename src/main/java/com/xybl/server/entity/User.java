package com.xybl.server.entity;

/**
 * User
 * <p>实体类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class User {
    public User(String id, String name, int role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    private String id;
    private String name;
    private String pwd;
    private int role;
    private String email;
    private String tel;

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
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}