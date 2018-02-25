package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/19.
 */

public class ConversationMsg {
    private int head;
    private String msg;
    private boolean ifme;

    public ConversationMsg(int head, String msg, boolean ifme) {
        this.head = head;
        this.msg = msg;
        this.ifme = ifme;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIfme() {
        return ifme;
    }

    public void setIfme(boolean ifme) {
        this.ifme = ifme;
    }

    @Override
    public String toString() {
        return "ConversationMsg{" +
                "head=" + head +
                ", msg='" + msg + '\'' +
                ", ifme=" + ifme +
                '}';
    }
}
