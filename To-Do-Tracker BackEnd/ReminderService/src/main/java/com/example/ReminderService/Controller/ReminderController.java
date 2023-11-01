package com.example.ReminderService.Controller;

import com.example.ReminderService.Exception.TaskNotFoundException;
import com.example.ReminderService.Service.ReminderServiceImpl;
import com.example.TaskService.Domain.Task;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class ReminderController {
	private ReminderServiceImpl service;
	
	@Autowired
	public ReminderController(ReminderServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping("/gettasks")
	public ResponseEntity gettasks(HttpServletRequest request) {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			System.out.println ("Authheader:" + authheader);
			String emailId = claims.getSubject ();
			return new ResponseEntity<> (service.getTasks (emailId, authheader), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
	
	@GetMapping("/today")
	public ResponseEntity<List<Task>> getTasksForToday(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.todayTasks (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (TaskNotFoundException e) {
			throw new TaskNotFoundException ("You have no tasks for this day");
		}
	}
	@GetMapping("/todayTasksByPriority")
	public ResponseEntity<?> getTasksTodayByPriority(String email, HttpServletRequest request) {
		Claims claims = (Claims) request.getAttribute("claims");//retrieving the claims from http request
		String authheader = request.getHeader("Authorization");
		List<Task> tasksForToday = service.todayTaskSortedByPriority(email, authheader);
		
		if (tasksForToday.isEmpty()) {
			String errorMessage = "You have no tasks " + LocalDate.now() + " this day";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(tasksForToday, HttpStatus.OK);
	}


	@GetMapping("/todayTasksByCompletion")
	public ResponseEntity<List<Task>> getTasksForTodayByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.todayTaskSortedCompletionStatus (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/todayTasksByTime")
	public ResponseEntity<List<Task>> getTasksForTodayByTime(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.todayTaskSortedByTime (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	@GetMapping("/tomorrow")
	public ResponseEntity<List<Task>> getTasksForTomorrow(String email, HttpServletRequest request)throws TaskNotFoundException
	{
		try
		{
			Claims claims = (Claims) request.getAttribute ("claims");
			String authHeader = request.getHeader ("Authorization");
			List<Task> tasksForTomorrow = service.tomorrowTasks (email, authHeader);
			if (tasksForTomorrow.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForTomorrow, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tomorrowTasksByPriority")
	public ResponseEntity<List<Task>> getTasksTomorrowByPriority(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.tomorrowTaskSortedByPriority (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tomorrowTasksByCompletion")
	public ResponseEntity<List<Task>> getTasksForTomorrowByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.tomorrowTaskSortedCompletionStatus(email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tomorrowTasksByTime")
	public ResponseEntity<List<Task>> getTasksForTomorrowByTime(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.tomorrowTaskSortedByTime (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/restOfTheTasks")
	public ResponseEntity<List<Task>> restOfTheTasks(String email, HttpServletRequest request) {
		try
		{
			Claims claims = (Claims) request.getAttribute ("claims");
			String authHeader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.restOfTheTasks (email, authHeader);
			if (tasksForToday.isEmpty ())
			{
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/restOfTheTaskByPriority")
	public ResponseEntity<List<Task>> getRestOfTheTaskByPriority(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.restOfTheTaskSortedByPriority(email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/restOfTheTasksByCompletion")
	public ResponseEntity<List<Task>> getRestOfTheTasksByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.restOfTheTaskSortedCompletionStatus(email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/restOfTheTaskByDate")
	public ResponseEntity<List<Task>> getRestOfTheTaskByDate(String email, HttpServletRequest request) throws TaskNotFoundException {
		try {
			Claims claims = (Claims) request.getAttribute ("claims");
			String authheader = request.getHeader ("Authorization");
			List<Task> tasksForToday = service.restOfTheTaskSortedByDate (email, authheader);
			
			if (tasksForToday.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/pastTasks")
	public ResponseEntity<List<Task>> getPastTasks(String email, HttpServletRequest request)throws TaskNotFoundException
	{
		try
		{
			Claims claims = (Claims) request.getAttribute ("claims");
			String authHeader = request.getHeader ("Authorization");
			List<Task> tasksForTomorrow = service.pastTasks (email, authHeader);
			if (tasksForTomorrow.isEmpty ()) {
				throw new TaskNotFoundException ("You have no tasks for this day");
			}
			return new ResponseEntity<> (tasksForTomorrow, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
