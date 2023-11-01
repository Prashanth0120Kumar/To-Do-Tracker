package com.example.TaskService.Exception;

//@ResponseStatus(code= HttpStatus.CONFLICT,reason="The Task is not Found")

public class TaskNotFoundException extends Exception
{
	public TaskNotFoundException (String message)
	{
		super(message);
	}
	
}
