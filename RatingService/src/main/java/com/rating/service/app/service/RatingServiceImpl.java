package com.rating.service.app.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.app.entity.Rating;
import com.rating.service.app.exception.RatingException;
import com.rating.service.app.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) throws RatingException {
		
		String randomMovieId = UUID.randomUUID().toString();
		rating.setRatingId(randomMovieId);
		Rating newRating = ratingRepository.save(rating);
		if (newRating != null) {
			return newRating;
		}
		else {
			throw new RatingException("Error");
		}
	}

	@Override
	public List<Rating> getAllRating() throws RatingException {
		
		List<Rating> allRatings = ratingRepository.findAll();
		if (allRatings.isEmpty()) {
			throw new RatingException("Empty List");
		}
		else {
			return allRatings;
		}
	}
	
	@Override
	public Rating getRatingById(String ratingId) throws RatingException {
		
		Optional<Rating> opt = ratingRepository.findById(ratingId);
		if (opt == null) {
			throw new RatingException("Not found");
		}
		else {
			return opt.get();
		}
	}
	
	
	@Override
	public List<Rating> getRatingsByUserId(String userId) throws RatingException {
		
//		List<Rating> ratings = ratingRepository.findByUserId(userId);
//		if (ratings.isEmpty()) {
//			throw new RatingException("Empty List");
//		}
//		else {
//			return ratings;
//		}
		
		List<Rating> ratings = ratingRepository.findByUserId(userId);
		return ratings;
	}

	@Override
	public List<Rating> getRatingsByMovieId(String movieId) throws RatingException {
		
		List<Rating> ratings = ratingRepository.findByMovieId(movieId);
		if (ratings.isEmpty()) {
			throw new RatingException("Empty List");
		}
		else {
			return ratings;
		}
	}

	

	
}
