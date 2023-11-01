package com.example.Authorization.Controller;

import com.example.Authorization.Domain.User;
import com.example.Authorization.Exception.InvalidCredentialsException;
import com.example.Authorization.Exception.UserAlreadyExistsException;
import com.example.Authorization.Security.SecurityTokenImpl;
import com.example.Authorization.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	private UserServiceImpl service;
	private SecurityTokenImpl tokengenerator;
	@Autowired
	public UserController(UserServiceImpl service, SecurityTokenImpl tokenGenerator) {
		this.service = service;
		this.tokengenerator = tokenGenerator;
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity saveUser(@RequestBody User user)throws UserAlreadyExistsException {
		return new ResponseEntity<>(service.saveUser (user), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity loginuser(@RequestBody User user)throws InvalidCredentialsException {
		if(service.getUserByUserEmailAndPassword (user.getUserEmail (), user.getUserPassword ())==null){
			throw new InvalidCredentialsException();
		}
		String token=tokengenerator.createToken(user);
		return new ResponseEntity(token,HttpStatus.OK);
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity getUsers(){
		return new ResponseEntity<>(service.getUserDetails (), HttpStatus.OK);
	}
}