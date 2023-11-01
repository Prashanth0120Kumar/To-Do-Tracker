package com.example.TaskService.Exception;

//@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="User Not Found")
public class UserNotFoundException extends Exception
{
	public UserNotFoundException(String message){
		
		super(message);
}
}
