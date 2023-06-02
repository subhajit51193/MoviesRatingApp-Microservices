package com.user.service.app.service;

import java.util.List;

import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;

public interface UserService {

	public User createUser(User user) throws UserException;
	
	public List<User> getAllUsers() throws UserException;
	
	public User getUserById(String id) throws UserException;
	
	public User deleteUser(User user)throws UserException;
	
	public User updateUser(String userId,User user)throws UserException;
	
}
