package com.rating.service.app.service;

import java.util.List;

import com.rating.service.app.entity.Rating;
import com.rating.service.app.exception.RatingException;

public interface RatingService {

	public Rating createRating(Rating rating)throws RatingException;
	
	public List<Rating> getAllRating()throws RatingException;
	
	public List<Rating> getRatingsByUserId(String userId);
	
	public List<Rating> getRatingsByMovieId(String movieId);
}
