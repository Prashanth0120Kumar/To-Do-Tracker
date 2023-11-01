package com.example.ReminderService.Proxy;

import com.example.TaskService.Domain.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "task-service", url = "http://localhost:8087")
public interface ReminderProxy
{
	@GetMapping("/api/v2/user/allTasks")
	public ResponseEntity<List<Task>> getAllUserTasks(@RequestParam("userEmail") String userEmail, @RequestHeader("Authorization") String authHeader);
	
}