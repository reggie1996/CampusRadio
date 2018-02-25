package com.example.a24073.campusradio.model.bean;

import java.util.Date;

public class Message {
	private Integer mid;
	private Employee sender;
	private Date sendTime;
	private String words;
	private String picture;
	private String ifHot;
	private Integer likeNum;
	private String senderName;
	private String senderIcon;
	private String senderSchool;

	public Message(String senderIcon, String senderName, String senderSchool, Date sendTime, String words, String picture) {
		this.senderIcon = senderIcon;
		this.senderName = senderName;
		this.senderSchool = senderSchool;
		this.sendTime = sendTime;
		this.words = words;
		this.picture = picture;
	}

	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderIcon() {
		return senderIcon;
	}
	public void setSenderIcon(String senderIcon) {
		this.senderIcon = senderIcon;
	}
	public String getSenderSchool() {
		return senderSchool;
	}
	public void setSenderSchool(String senderSchool) {
		this.senderSchool = senderSchool;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Employee getSender() {
		return sender;
	}
	public void setSender(Employee sender) {
		this.sender = sender;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getIfHot() {
		return ifHot;
	}
	public void setIfHot(String ifHot) {
		this.ifHot = ifHot;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", sender=" + sender + ", sendTime=" + sendTime + ", words=" + words
				+ ", picture=" + picture + ", ifHot=" + ifHot + ", likeNum=" + likeNum + ", senderName=" + senderName
				+ ", senderIcon=" + senderIcon + ", senderSchool=" + senderSchool + "]";
	}

	
}
