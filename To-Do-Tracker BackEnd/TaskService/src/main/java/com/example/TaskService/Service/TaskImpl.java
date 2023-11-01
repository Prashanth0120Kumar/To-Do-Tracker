package com.example.TaskService.Service;

import com.example.TaskService.Domain.Priority;
import com.example.TaskService.Domain.Task;
import com.example.TaskService.Domain.User;
import com.example.TaskService.Exception.TaskAlreadyExistsException;
import com.example.TaskService.Exception.TaskNotFoundException;
import com.example.TaskService.Exception.UserAlreadyExistsException;
import com.example.TaskService.Exception.UserNotFoundException;
import com.example.TaskService.Proxy.TaskProxy;
import com.example.TaskService.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class TaskImpl implements TaskService {
	private TaskRepository repos;
	private TaskProxy proxy;
	
	@Autowired
	public TaskImpl(TaskRepository repos, TaskProxy proxy) {
		this.repos = repos;
		this.proxy = proxy;
	}
	
	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		if (repos.findById (user.getUserEmail ()).isPresent ()) {
			throw new UserAlreadyExistsException ("User with email" + user.getUserEmail () + "already exists.");
		}
		User registerUser = repos.save (user);
		if (!(registerUser.getUserEmail ().isEmpty ())) {
			ResponseEntity<User> reg = proxy.saveUser (user);
			System.out.println (reg.getBody ());
		}
		return registerUser;
	}
	
	
	@Override
	public User addTasktoList(String userEmail, Task task) throws UserNotFoundException, TaskAlreadyExistsException {
		Optional<User> optionalUser = repos.findById (userEmail);
		if (optionalUser.isPresent ()) {
			User user = optionalUser.get ();
			for (Task existingTask : user.getTasks ()) {
				if (existingTask.getTaskID () == task.getTaskID ()) {
					throw new TaskAlreadyExistsException ("Task with ID " + task.getTaskID () + " already exists.");
				}
			}
			user.getTasks ().add (task);
			repos.save (user);
			return user;
		} else {
			throw new UserNotFoundException ("User with ID " + userEmail + " not found.");
		}
	}
	
	@Override
	public Task getTaskByID(String userEmail, int taskID)
	{
			Optional<User> optionalUser = repos.findById(userEmail);
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				List<Task> userTasks = user.getTasks();
				
				for (Task task : userTasks) {
					if (task.getTaskID() == taskID) {
						return task;
					}
				}
		}
		return null;
	}
	
	@Override
	public User updateExistingTask(String userEmail, int taskID, Task updatedTask) throws UserNotFoundException, TaskNotFoundException {
		Optional<User> optionalUser = repos.findById (userEmail);
		if (optionalUser.isPresent ()) {
			User user = optionalUser.get ();
			
			boolean taskUpdated = false;
			for (Task existingTask : user.getTasks ()) {
				if (existingTask.getTaskID () == taskID) {
					existingTask.setTaskHeading (updatedTask.getTaskHeading ());
					existingTask.setTaskContent (updatedTask.getTaskContent ());
					existingTask.setDueDate (updatedTask.getDueDate ());
					existingTask.setPriorityLevel (updatedTask.getPriorityLevel ());
					existingTask.setCategory (updatedTask.getCategory ());
					existingTask.setCompleted (updatedTask.isCompleted ());
					existingTask.setDueTime (updatedTask.getDueTime ());
					taskUpdated = true;
					break;
				}
			}
			if (taskUpdated) {
				repos.save (user);
				return user;
			} else {
				throw new TaskNotFoundException ("Task with ID " + taskID + " not found.");
			}
		} else {
			throw new UserNotFoundException ("User with ID " + userEmail + " not found.");
		}
	}
	
	@Override
	public boolean deleteExistingTask(String userEmail, int taskID) throws UserNotFoundException, TaskNotFoundException {
		Optional<User> optionalUser = repos.findById (userEmail);
		if (optionalUser.isPresent ()) {
			User user = optionalUser.get ();
			
			boolean taskRemoved = user.getTasks ().removeIf (task -> task.getTaskID () == taskID);
			
			if (taskRemoved) {
				repos.save (user);
				return true;
			} else {
				throw new TaskNotFoundException ("Task with ID " + taskID + " not found.");
			}
		} else {
			throw new UserNotFoundException ("User with ID " + userEmail + " not found.");
		}
	}
	@Override
	public User updateTaskPriority(String userID, int taskID, String priorityLevel) throws UserNotFoundException, TaskNotFoundException {
		{
			Optional<User> optionalUser = repos.findById (userID);
			if (optionalUser.isEmpty ()) {
				throw new UserNotFoundException ("");
			}
			User user = optionalUser.get ();
			List<Task> userTasks = user.getTasks ();
			for (Task task : userTasks) {
				if (task.getTaskID () == taskID) {
					userTasks.remove (task);
					task.setPriorityLevel (Priority.valueOf (priorityLevel));
					userTasks.add (task);
					user.setTasks (userTasks);
					return repos.save (user);
				}
			}
			throw new TaskNotFoundException ("");
		}
	}
	private List<Task> archivedTasks = new ArrayList<>();
	private List<Task> completedTasks = new ArrayList<>();
	
	@Override
	public List<Task> getAllUserTasks(String userEmail) throws UserNotFoundException {
		Optional<User> optionalUser = repos.findById(userEmail);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Task> allUserTasks = user.getTasks();
			Iterator<Task> iterator = allUserTasks.iterator();
			
			while (iterator.hasNext()) {
				Task task = iterator.next();
				
				if (task.isCompleted() && task.isArchived()) {
					archivedTasks.add(task);
					iterator.remove();
				} else if (task.isCompleted()) {
					completedTasks.add(task);
					iterator.remove();
				}
			}
			
			return allUserTasks;
		} else {
			throw new UserNotFoundException("User with email " + userEmail + " not found.");
		}
	}
	@Override
	public User updateTaskArchive(String userEmail, int taskID, boolean isArchived) throws UserNotFoundException, TaskNotFoundException {
		Optional<User> optionalUser = repos.findById(userEmail);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found: " + userEmail);
		}
		User user = optionalUser.get();
		List<Task> userTasks = user.getTasks();
		boolean taskUpdated = false;
		for (Task task : userTasks) {
			if (task.getTaskID() == taskID) {
				task.setArchived (isArchived);
				taskUpdated = true;
				break;
			}
		}
		if (taskUpdated) {
			user.setTasks(userTasks);
			return repos.save(user);
		} else {
			throw new TaskNotFoundException("Task not found: " + taskID);
		}
	}
	
	@Override
	public User updateTaskComplete(String userEmail, int taskID, boolean isComplete) throws UserNotFoundException, TaskNotFoundException {
		Optional<User> optionalUser = repos.findById(userEmail);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found: " + userEmail);
		}
		User user = optionalUser.get();
		List<Task> userTasks = user.getTasks();
		boolean taskUpdated = false;
		
		for (Task task : userTasks) {
			if (task.getTaskID() == taskID) {
				task.setCompleted(isComplete);
				taskUpdated = true;
				break;
			}
		}
		
		if (taskUpdated) {
			user.setTasks(userTasks);
			return repos.save(user);
		} else {
			throw new TaskNotFoundException("Task not found: " + taskID);
		}
	}
	@Override
	public List<Task> getArchivedTasks(String userEmail) {
		Set<Integer> seenTaskIds = new HashSet<> ();
		List<Task> uniqueArchivedTasks = new ArrayList<>();
		
		for (Task task : archivedTasks) {
			if (seenTaskIds.add(task.getTaskID ())) {
				uniqueArchivedTasks.add(task);
			}
		}
		
		archivedTasks.clear();
		
		return uniqueArchivedTasks;
	}
	@Override
	public List<Task> getCompletedTasks(String userEmail) {
		Set<Integer> seenTaskIds = new HashSet<>();
		List<Task> uniqueCompletedTasks = new ArrayList<>();
		
		for (Task task : completedTasks) {
			if (seenTaskIds.add(task.getTaskID ())) {
				uniqueCompletedTasks.add(task);
			}
		}
		
		completedTasks.clear();
		
		return uniqueCompletedTasks;
	}
	
}



