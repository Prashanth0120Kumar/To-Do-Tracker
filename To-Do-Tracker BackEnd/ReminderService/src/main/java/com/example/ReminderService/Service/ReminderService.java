package com.example.ReminderService.Service;

import com.example.TaskService.Domain.Task;

import java.util.List;

public interface ReminderService
{
	List<Task> getTasks(String email,String authheader);
	List<Task> todayTasks(String email,String authHeader);
	List<Task> todayTaskSortedByPriority(String email,String authHeader);
	List<Task> todayTaskSortedCompletionStatus(String email,String authHeader);
	List<Task> todayTaskSortedByTime(String email,String authHeader);
	List<Task> tomorrowTasks(String email,String authHeader) ;
	List<Task> tomorrowTaskSortedByPriority(String email,String authHeader);
	List<Task> tomorrowTaskSortedCompletionStatus(String email,String authHeader);
	List<Task> tomorrowTaskSortedByTime(String email,String authHeader);
	List<Task> restOfTheTasks(String email,String authHeader) ;
	List<Task> restOfTheTaskSortedByPriority(String email,String authHeader);
	List<Task> restOfTheTaskSortedCompletionStatus(String email,String authHeader);
	List<Task> restOfTheTaskSortedByDate(String email,String authHeader);
	List<Task> pastTasks(String email,String authHeader);
	Task findTaskById(Long taskId, String authHeader); // Add the new method signature here
}
