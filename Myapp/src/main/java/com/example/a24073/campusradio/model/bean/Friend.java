package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/4.
 */

public class Friend {
    private int portrait;
    private int shu;
    private  String name;
    private String id;

    public Friend() {
    }

    public Friend(int portrait, int shu, String name, String id) {
        this.portrait = portrait;
        this.shu = shu;
        this.name = name;
        this.id = id;
    }

    public int getShu() {
        return shu;
    }

    public void setShu(int shu) {
        this.shu = shu;
    }

    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "portrait=" + portrait +
                ", shu=" + shu +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
