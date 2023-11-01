package com.example.Authorization.Service;

import com.example.Authorization.Domain.User;
import com.example.Authorization.Exception.InvalidCredentialsException;
import com.example.Authorization.Exception.UserAlreadyExistsException;
import com.example.Authorization.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository repos;
	
	@Autowired
	public UserServiceImpl(UserRepository repos) {
		this.repos = repos;
	}
	
	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		if (repos.findById (user.getUserEmail ()).isPresent ()) {
			throw new UserAlreadyExistsException ();
		} else {
			repos.save (user);
			return user;
		}
	}
	
	@Override
	public User getUserByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialsException {
		User details = repos.findByUserEmailAndUserPassword (userEmail, password);
		if (details == null) {
			throw new InvalidCredentialsException ();
		} else {
			return details;
		}
	}
	
	@Override
	public List<User> getUserDetails() {
		return repos.findAll();
	}

}