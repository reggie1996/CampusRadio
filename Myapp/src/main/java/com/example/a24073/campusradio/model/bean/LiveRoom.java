package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/16.
 */

public class LiveRoom {
    private String liveRoomCover;
    private String roomName;
    private String hostName;
    private int likeNum;
    private boolean iflive;

    public LiveRoom(String liveRoomCover, String roomName, String hostName, int likeNum, boolean iflive) {
        this.liveRoomCover = liveRoomCover;
        this.roomName = roomName;
        this.hostName = hostName;
        this.likeNum = likeNum;
        this.iflive = iflive;
    }

    public String getLiveRoomCover() {
        return liveRoomCover;
    }

    public void setLiveRoomCover(String liveRoomCover) {
        this.liveRoomCover = liveRoomCover;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public boolean isIflive() {
        return iflive;
    }

    public void setIflive(boolean iflive) {
        this.iflive = iflive;
    }

    @Override
    public String toString() {
        return "LiveRoom{" +
                "liveRoomCover=" + liveRoomCover +
                ", roomName='" + roomName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", likeNum=" + likeNum +
                '}';
    }
}
