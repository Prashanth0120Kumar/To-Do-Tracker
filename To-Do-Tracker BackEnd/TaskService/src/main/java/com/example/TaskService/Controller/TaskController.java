package com.example.TaskService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TaskService.Domain.Task;
import com.example.TaskService.Domain.User;
import com.example.TaskService.Exception.TaskAlreadyExistsException;
import com.example.TaskService.Exception.TaskNotFoundException;
import com.example.TaskService.Exception.UserAlreadyExistsException;
import com.example.TaskService.Exception.UserNotFoundException;
import com.example.TaskService.Service.TaskImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//@CrossOrigin
@RestController

	@RequestMapping("/api/v2")
	public class TaskController {
	private TaskImpl service;
	
	@Autowired
	public TaskController(TaskImpl service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {
		return new ResponseEntity<> (service.registerUser (user), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/getTaskById/{taskID}")
	public Task getTaskByID(@PathVariable int taskID, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
		Claims claims = (Claims) request.getAttribute ("claims");
		String userEmail = claims.getSubject ();
		if (userEmail.equals (claims.getSubject ())) {
			return service.getTaskByID (userEmail, taskID);
		}
		return null;
	}
	

	@PostMapping("/user/addTask")
	public ResponseEntity<User> addTaskToList(@RequestBody Task task, HttpServletRequest request) {
		Claims claims = (Claims) request.getAttribute ("claims");
		String userEmail = claims.getSubject ();
		try {
			User updatedUser = service.addTasktoList (userEmail, task);
			return new ResponseEntity<> (updatedUser, HttpStatus.CREATED);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} catch (TaskAlreadyExistsException e) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("user/updateTask/task/{taskID}")
	public ResponseEntity<User> updateExistingTask(@RequestBody Task task, @PathVariable int taskID, HttpServletRequest request)
		throws UserNotFoundException, TaskNotFoundException {
		Claims claims = (Claims) request.getAttribute ("claims");
		String userEmail = claims.getSubject ();
		// Check if the token's subject (user) matches the subject of the token generated during login.
		if (userEmail.equals (claims.getSubject ())) {
			return new ResponseEntity<> (service.updateExistingTask (userEmail, taskID, task), HttpStatus.OK);
		} else {
			// Handle unauthorized access (e.g., return 401 Unauthorized).
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
	}
	
	@DeleteMapping("user/deleteTask/{taskID}")
	public ResponseEntity<String> deleteExistingTask(@PathVariable int taskID, HttpServletRequest request)
		throws UserNotFoundException, TaskNotFoundException {
		Claims claims = (Claims) request.getAttribute ("claims");
		String userEmail = claims.getSubject ();
		if (userEmail.equals (claims.getSubject ())) {
			service.deleteExistingTask (userEmail, taskID);
			return new ResponseEntity<> ("Task deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PutMapping("/user/updateTaskPriority/task/{taskID}/priority")
	public ResponseEntity<?> updateTaskPriorityLevel(@PathVariable int taskID, @RequestBody String priorityLevel, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			return new ResponseEntity<> (service.updateTaskPriority (userEmail, taskID, priorityLevel), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			throw e;
		} catch (TaskNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace ();
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/allTasks")
	public ResponseEntity<List<Task>> getAllUserTasks(HttpServletRequest request) {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			String token = "Bearer" + request.getHeader ("Authorization");
			List<Task> tasks = service.getAllUserTasks (userEmail);
			return new ResponseEntity<> (tasks, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/archived")
	public ResponseEntity<List<Task>> getArchivedTasks(HttpServletRequest request) {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			String token = "Bearer" + request.getHeader ("Authorization");
			List<Task> task = service.getAllUserTasks (userEmail);
			List<Task> tasks = service.getArchivedTasks (userEmail);
			return new ResponseEntity<> (tasks, HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
	@GetMapping("/user/completed")
	public ResponseEntity<List<Task>> getCompletedTasks(HttpServletRequest request) {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			String token = "Bearer" + request.getHeader ("Authorization");
			List<Task> task = service.getAllUserTasks (userEmail);
			List<Task> tasks = service.getCompletedTasks (userEmail);
			return new ResponseEntity<> (tasks, HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
	@PutMapping("/user/updateTaskCompletion/task/{taskID}/isComplete")
	public ResponseEntity<?> updateTaskCompletion(@PathVariable int taskID, @RequestBody boolean isComplete, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			return new ResponseEntity<> (service.updateTaskComplete (userEmail, taskID, isComplete), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			throw e;
		} catch (TaskNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace ();
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/user/updateTaskArchive/task/{taskID}/isArchive")
	public ResponseEntity<?> updateTaskArchive(@PathVariable int taskID, @RequestBody boolean isArchive, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String userEmail = claims.getSubject ();
			return new ResponseEntity<> (service.updateTaskArchive (userEmail, taskID, isArchive), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			throw e;
		} catch (TaskNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace ();
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	

	

