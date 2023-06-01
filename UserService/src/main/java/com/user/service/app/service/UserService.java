package com.user.service.app.service;

import java.util.List;

import com.user.service.app.entity.User;

public interface UserService {

	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(String id);
	
}
