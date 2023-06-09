package com.user.service.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.app.entity.Movie;
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
//				getting ratings for each userID
				Rating[] ratingsOfUser  = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
//				logger.info("{}",ratingsOfUser);
//				converting to arraylist so that it can be set as list
				List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
				for (Rating rating : ratingsOfUser) {
//					http://localhost:8082/movie/5feca494-9808-4958-8dcb-e02e7a62b956
//					getting movie for each movieId from rating
					Movie movie = restTemplate.getForObject("http://MOVIE-SERVICE/movie/"+rating.getMovieId(), Movie.class);
//					setting movie inside rating
					rating.setMovie(movie);
					logger.info("{}",movie);
				}
//				seting ratings inside users
				user.setRatings(ratings);
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
//			getting ratings from userId
			Rating[] ratingsOfUser  = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
//			logger.info("{}",ratingsOfUser);
			List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//			setting movie to rating
			for (Rating rating : ratingsOfUser) {
//				http://localhost:8082/movie/5feca494-9808-4958-8dcb-e02e7a62b956
//				getting movie for each movieId from rating
				Movie movie = restTemplate.getForObject("http://MOVIE-SERVICE/movie/"+rating.getMovieId(), Movie.class);
//				setting movie inside rating 
				rating.setMovie(movie);
			}
//			seting ratings inside users
			user.setRatings(ratings);
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
