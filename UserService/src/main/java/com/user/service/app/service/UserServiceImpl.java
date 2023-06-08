package com.user.service.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.app.entity.Rating;
import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;
import com.user.service.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User createUser(User user) throws UserException {
		
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
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
			
			for(User user : users) {
				ArrayList<Rating> ratingsOfUser  = restTemplate.getForObject("http://localhost:8083/rating/users/"+user.getUserId(), ArrayList.class);
				logger.info("{}",ratingsOfUser);
				user.setRatings(ratingsOfUser);
			}
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
//			getting rating from object
			ArrayList<Rating> ratingsOfUser  = restTemplate.getForObject("http://localhost:8083/rating/users/"+user.getUserId(), ArrayList.class);
			logger.info("{}",ratingsOfUser);
			user.setRatings(ratingsOfUser);
			return user;
			
		}
	}

	@Override
	public User deleteUser(User user) throws UserException {
		
		if (user == null) {
			throw new UserException("Not found");
		}
		else {
			userRepository.delete(user);
			return user;
		}
	}

	@Override
	public User updateUser(String userId,User user) throws UserException {
		
		Optional<User> opt = userRepository.findById(userId);
		if (opt.isEmpty()) {
			throw new UserException("User not found");
		}
		else {
			User foundUser = opt.get();
			foundUser.setName(user.getName());
			foundUser.setEmail(user.getEmail());
			foundUser.setAge(user.getAge());
			foundUser.setAbout(user.getAbout());
			return userRepository.save(foundUser);
		}
	}

	

	
	
	

}
