package com.xybl.server.entity;

public class Appeal {
    private String id;
    private String al_time;
    private String al_address;
    private String al_abstract;
    private String detail;
    private String appellant;
    private String acceptor;
    private int progress;
    private boolean re_appeal;

    @Override
    public String toString() {
        return "Appeal{" +
                "id='" + id + '\'' +
                ", al_time='" + al_time + '\'' +
                ", al_address='" + al_address + '\'' +
                ", al_abstract='" + al_abstract + '\'' +
                ", detail='" + detail + '\'' +
                ", appellant='" + appellant + '\'' +
                ", acceptor='" + acceptor + '\'' +
                ", progress=" + progress +
                ", re_appeal=" + re_appeal +
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

    public String getAl_address() {
        return al_address;
    }

    public void setAl_address(String al_address) {
        this.al_address = al_address;
    }

    public String getAl_abstract() {
        return al_abstract;
    }

    public void setAl_abstract(String al_abstract) {
        this.al_abstract = al_abstract;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAppellant() {
        return appellant;
    }

    public void setAppellant(String appellant) {
        this.appellant = appellant;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isRe_appeal() {
        return re_appeal;
    }

    public void setRe_appeal(boolean re_appeal) {
        this.re_appeal = re_appeal;
    }
}
