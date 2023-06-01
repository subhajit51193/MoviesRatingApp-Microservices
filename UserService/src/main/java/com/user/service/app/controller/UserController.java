package com.user.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;
import com.user.service.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUserHandler(@RequestBody User user) throws UserException{
		
		User newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsersHandler() throws UserException{
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByIdhandler(@PathVariable("id") String userId) throws UserException{
		User foundUser = userService.getUserById(userId);
		return new ResponseEntity<User>(foundUser,HttpStatus.ACCEPTED);
	}
}
