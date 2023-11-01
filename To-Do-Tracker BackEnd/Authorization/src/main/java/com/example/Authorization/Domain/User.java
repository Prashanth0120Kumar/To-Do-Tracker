package com.example.Authorization.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	String userEmail;
	String userPassword;
	public User(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	public User() {
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "User{" +
					 "userEmail='" + userEmail + '\'' +
					 ", userPassword='" + userPassword + '\'' +
					 '}';
	}
}
