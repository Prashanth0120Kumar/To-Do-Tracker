package com.example.TaskService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason="The Task You are trying to create already exists")
public class TaskAlreadyExistsException extends  Exception
{
	public TaskAlreadyExistsException(String message)
	{
		super(message);
	}
	
}
