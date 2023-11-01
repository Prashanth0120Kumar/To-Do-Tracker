package com.example.TaskService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User
{
	@Id
	String userEmail;
	String userPassword;
	String userName;
	private List<Task> tasks = new ArrayList<> ();
	
	public User() {
	}
	
	public User(String userEmail, String userPassword, String userName, List<Task> tasks) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.tasks = tasks;
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
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return "User{" +
					 "userEmail='" + userEmail + '\'' +
					 ", userPassword='" + userPassword + '\'' +
					 ", userName='" + userName + '\'' +
					 ", tasks=" + tasks +
					 '}';
	}
}
