package com.example.a24073.campusradio.model.bean;

import java.util.Date;

public class Employee {

	private Integer id;
	// 不能被修改
	private String lastName;
	private String icon;
	private String password;

	// 从前端传入的是 String 类型, 所以需要注意转换
	private Date birth;

	// 不能被修改
	private Date createTime;
	// 单向 n-1 的关联关系
	private String school;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", icon=" + icon + ", password=" + password
				+ ", birth=" + birth + ", createTime=" + createTime + ", school=" + school + "]";
	}

}
