package com.xybl.server.entity;

/**
 * Student
 * <p></p>
 *
 * @author liubocai
 * @create 2021-02-25
 **/
public class Student extends User {
    private String stu_name;
    private String tel;
    private String address;
    private String email;

    public Student(String id, String name) {
        super(id, name, false);
    }
    public Student(String id, String name, String pwd) {
        super(id, name, pwd,false);
    }
    public Student() {

    }
    public Student(String id){super(id);}

    public Student(String id, String name, String stu_name, String tel, String address, String email) {
        super(id, name, false);
        this.stu_name = stu_name;
        this.tel = tel;
        this.address = address;
        this.email = email;
    }

    public Student(User user) {
        super(user.getId(), user.getName(), user.getPwd(), user.getRole());
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_name='" + stu_name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", pwd='" + super.getPwd() + '\'' +
                '}';
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPwd() {
        return super.getPwd();
    }

    @Override
    public void setPwd(String pwd) {
        super.setPwd(pwd);
    }
}
