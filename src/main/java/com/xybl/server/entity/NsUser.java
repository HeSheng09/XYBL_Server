package com.xybl.server.entity;

/**
 * NsUser
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
public class NsUser extends User{
    private String ns_name;
    private String privilege;
    private String tel;
    private String email;


    public NsUser(String id, String name){
        super(id, name, true);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "NsUser{" +
                "ns_name='" + ns_name + '\'' +
                ", privilege='" + privilege + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", id='"+getId()+'\''+
                ", name='"+getName()+'\''+
                ", pwd='"+getPwd()+'\''+
                '}';
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

    public String getNs_name() {
        return ns_name;
    }

    public void setNs_name(String ns_name) {
        this.ns_name = ns_name;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
