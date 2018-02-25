package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/22.
 */

public class LiveCourse {
    private String courseName;
    private String hostName;
    private String describe;
    private String cover;

    public LiveCourse(String courseName, String hostName, String describe, String cover) {
        this.courseName = courseName;
        this.hostName = hostName;
        this.describe = describe;
        this.cover = cover;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "LiveCourse{" +
                "courseName='" + courseName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", describe='" + describe + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
