package com.example.a24073.campusradio.utils;

/**
 * Created by 24073 on 2017/7/24.
 */

public class LiveRoomConversationEvent {
    private int type;

    public LiveRoomConversationEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
