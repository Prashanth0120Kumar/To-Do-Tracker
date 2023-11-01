package com.example.Authorization.Service;

import com.example.Authorization.Domain.User;
import com.example.Authorization.Exception.InvalidCredentialsException;
import com.example.Authorization.Exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {
	User saveUser(User user) throws UserAlreadyExistsException;
	
	User getUserByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialsException;
	
	List getUserDetails();
}
