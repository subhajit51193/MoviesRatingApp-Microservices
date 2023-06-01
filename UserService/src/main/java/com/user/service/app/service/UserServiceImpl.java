package com.user.service.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;
import com.user.service.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public User createUser(User user) throws UserException {
		
		User newUser =  userRepository.save(user);
		if (newUser != null) {
			return newUser;
		}
		else {
			throw new UserException("Error Occured");
		}
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new UserException("Empty List");
		}
		else {
			return users;
		}
		
	}

	@Override
	public User getUserById(String id) throws UserException {
		
		Optional<User> opt = userRepository.findById(id);
		if (opt == null) {
			throw new UserException("Not found");
		}
		else {
			User user = opt.get();
			return user;
			
		}
	}
	
	

}
