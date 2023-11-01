package com.example.TaskService.Proxy;

import com.example.TaskService.Domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="Authorization",url = "localhost:8089")
public interface TaskProxy
{
	@PostMapping("/api/v1/saveUser")
	public ResponseEntity saveUser(@RequestBody User user);
}


