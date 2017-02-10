package com.basepack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** This is model class, it models table from db */
@Entity
public class UserDetails {

	/* Fields, that will go to db */
	@Id @GeneratedValue //@Id used for Primary Key @GeneratedValue for autogenerate	 
	private int userId;	
	private String userName;
		
	/* Getters and setters */
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	
}
