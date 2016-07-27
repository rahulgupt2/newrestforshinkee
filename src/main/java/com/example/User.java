package com.example;

import java.math.BigInteger;

public class User {

	
	private BigInteger userId;
	private String userName;
	private String userAddress;
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userAddress=" + userAddress + "]";
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger nextId) {
		this.userId = nextId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	
	
}
