package com.xybl.server.entity;

/**
 * Test
 * <p>实体类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class Test {
    private int id;
    private String info;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
