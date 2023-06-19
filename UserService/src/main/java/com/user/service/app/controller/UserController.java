package com.user.service.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;
import com.user.service.app.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUserHandler(@RequestBody User user) throws UserException{
		
		User newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	@CircuitBreaker(name = "ratingMovieBreaker",fallbackMethod = "ratingMovieFallback")
	public ResponseEntity<List<User>> getAllUsersHandler() throws UserException{
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name = "ratingMovieBreaker",fallbackMethod = "ratingMovieFallback")
	public ResponseEntity<User> getUserByIdhandler(@PathVariable("id") String userId) throws UserException{
		User foundUser = userService.getUserById(userId);
		return new ResponseEntity<User>(foundUser,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserHandler(@PathVariable("id") String userId) throws UserException{
		User user = userService.getUserById(userId);
		User deletedUser = userService.deleteUser(user);
		return new ResponseEntity<User>(deletedUser,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUserHandler(@PathVariable("id") String userId, @RequestBody User user) throws UserException{
		User updatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);
	}
	
	//fallback method for Circuit breaker
	public ResponseEntity<User> ratingMovieFallback(String userId,Exception ex){
		User user = new User();
		user.setUserId("9999");
		user.setEmail("dummy@gmail.com");
		user.setAge(99);
		user.setName("dummyName");
		user.setAbout("This is dummy user created because some services are down");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	public ResponseEntity<List<User>> ratingMovieFallback(Exception ex){
		User user1 = new User();
		user1.setUserId("9999");
		user1.setEmail("dummy@gmail.com");
		user1.setAge(99);
		user1.setName("dummyName");
		user1.setAbout("This is dummy user created because some services are down");
		List<User> list = new ArrayList<>();
		list.add(user1);
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	
}
