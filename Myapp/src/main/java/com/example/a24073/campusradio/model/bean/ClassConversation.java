package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/30.
 */

public class ClassConversation {
    int head;
    String info;
    boolean ifme;

    public ClassConversation(int head, String info, boolean ifme) {
        this.head = head;
        this.info = info;
        this.ifme = ifme;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isIfme() {
        return ifme;
    }

    public void setIfme(boolean ifme) {
        this.ifme = ifme;
    }
}
