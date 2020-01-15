package com.dealership.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -978646777271063913L;
	
	private int userId;
	private int userType;
	private String userName;
	private String userPass;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userType=" + userType + ", userName=" + userName + ", userPass=" + userPass
				+ "]";
	}
	public User(int userId, int userType, String userName, String userPass) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.userName = userName;
		this.userPass = userPass;
	}
	public User() {}
	
	public User(User u) {
		this.userId = u.userId;
		this.userType = u.userType;
		this.userName = u.userName;
		this.userPass = u.userPass;
	}
	
	
}
