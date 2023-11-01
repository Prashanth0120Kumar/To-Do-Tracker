package com.example.TaskService.Domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
	@Id
	private int taskID;
	private String taskHeading;
	private String taskContent;
	private LocalDate dueDate;
	private LocalTime dueTime;
//	private String dueTimeInput;
	private Priority priorityLevel;
	private String category;
	private boolean isCompleted;
	private LocalTime reminder;
	private boolean isArchived;
	
	public Task() {
	}
	
	public Task(int taskID, String taskHeading, String taskContent, LocalDate dueDate, LocalTime dueTime, Priority priorityLevel, String category, boolean isCompleted, LocalTime reminder, boolean isArchived) {
		this.taskID = taskID;
		this.taskHeading = taskHeading;
		this.taskContent = taskContent;
		this.dueDate = dueDate;
		this.dueTime = dueTime;
		this.priorityLevel = priorityLevel;
		this.category = category;
		this.isCompleted = isCompleted;
		this.reminder = reminder;
		this.isArchived = isArchived;
	}
	
	public int getTaskID() {
		return taskID;
	}
	
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	public String getTaskHeading() {
		return taskHeading;
	}
	
	public void setTaskHeading(String taskHeading) {
		this.taskHeading = taskHeading;
	}
	
	public String getTaskContent() {
		return taskContent;
	}
	
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public LocalTime getDueTime() {
		return dueTime;
	}
	
	public void setDueTime(LocalTime dueTime) {
		this.dueTime = dueTime;
	}
	
	public Priority getPriorityLevel() {
		return priorityLevel;
	}
	
	public void setPriorityLevel(Priority priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	
	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}
	
	public LocalTime getReminder() {
		return reminder;
	}
	
	public void setReminder(LocalTime reminder) {
		this.reminder = reminder;
	}
	
	public boolean isArchived() {
		return isArchived;
	}
	
	public void setArchived(boolean archived) {
		isArchived = archived;
	}
	
	@Override
	public String toString() {
		return "Task{" +
					 "taskID=" + taskID +
					 ", taskHeading='" + taskHeading + '\'' +
					 ", taskContent='" + taskContent + '\'' +
					 ", dueDate=" + dueDate +
					 ", dueTime=" + dueTime +
					 ", priorityLevel=" + priorityLevel +
					 ", category='" + category + '\'' +
					 ", isCompleted=" + isCompleted +
					 ", reminder=" + reminder +
					 ", isArchived=" + isArchived +
					 '}';
	}
}
