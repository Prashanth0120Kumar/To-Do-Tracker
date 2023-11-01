package com.example.TaskService.Exception;

//@ResponseStatus(code= HttpStatus.CONFLICT,reason="User Already Exists")
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
