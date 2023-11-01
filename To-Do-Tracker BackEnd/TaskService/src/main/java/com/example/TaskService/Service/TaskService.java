package com.example.TaskService.Service;

import com.example.TaskService.Domain.Task;
import com.example.TaskService.Domain.User;
import com.example.TaskService.Exception.TaskAlreadyExistsException;
import com.example.TaskService.Exception.TaskNotFoundException;
import com.example.TaskService.Exception.UserAlreadyExistsException;
import com.example.TaskService.Exception.UserNotFoundException;

import java.util.List;

public interface TaskService
{
	User registerUser(User user) throws UserAlreadyExistsException;

	User addTasktoList(String userEmail, Task task) throws UserNotFoundException, TaskAlreadyExistsException;
	Task getTaskByID(String userEmail,int taskID);
	User updateExistingTask(String userEmail, int taskID, Task task) throws UserNotFoundException, TaskNotFoundException;
	boolean deleteExistingTask(String userEmail, int taskID) throws UserNotFoundException, TaskNotFoundException;
	

	User updateTaskPriority(String userID, int taskID, String priorityLevel) throws UserNotFoundException, TaskNotFoundException;
 
	List<Task> getAllUserTasks(String userEmail) throws UserNotFoundException;
	public List<Task> getArchivedTasks(String userEmail);
	public User updateTaskComplete(String userEmail, int taskID, boolean isComplete) throws UserNotFoundException, TaskNotFoundException;
	public User updateTaskArchive(String userEmail, int taskID, boolean isArchived) throws UserNotFoundException, TaskNotFoundException;
	public List<Task> getCompletedTasks(String userEmail);
	
}
