package com.xybl.server.entity;

/**
 * Research
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
public class Research {
    private String id;
    private String handler;
    private String auditor;
    private String pro_time;
    private String result;
    private String res_time;
    private String filepath;
    private String app_comment;
    private String re_research;
    private String au_result;
    private String au_time;

    public Research() {
    }

    public Research(String id, String handler) {
        this.id = id;
        this.handler = handler;
    }

    public Research(String id, String handler, String pro_time) {
        this.id = id;
        this.handler = handler;
        this.pro_time = pro_time;
    }

    @Override
    public String toString() {
        return "Research{" +
                "id='" + id + '\'' +
                ", handler='" + handler + '\'' +
                ", auditor='" + auditor + '\'' +
                ", pro_time='" + pro_time + '\'' +
                ", result='" + result + '\'' +
                ", res_time='" + res_time + '\'' +
                ", filepath='" + filepath + '\'' +
                ", app_comment='" + app_comment + '\'' +
                ", re_research='" + re_research + '\'' +
                ", au_result='" + au_result + '\'' +
                ", au_time='" + au_time + '\'' +
                '}';
    }

    public String getAu_result() {
        return au_result;
    }

    public void setAu_result(String au_result) {
        this.au_result = au_result;
    }

    public String getAu_time() {
        return au_time;
    }

    public void setAu_time(String au_time) {
        this.au_time = au_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPro_time() {
        return pro_time;
    }

    public void setPro_time(String pro_time) {
        this.pro_time = pro_time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRes_time() {
        return res_time;
    }

    public void setRes_time(String res_time) {
        this.res_time = res_time;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getApp_comment() {
        return app_comment;
    }

    public void setApp_comment(String app_comment) {
        this.app_comment = app_comment;
    }

    public String getRe_research() {
        return re_research;
    }

    public void setRe_research(String re_research) {
        this.re_research = re_research;
    }
}
