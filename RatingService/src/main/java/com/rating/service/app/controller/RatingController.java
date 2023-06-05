package com.rating.service.app.controller;

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

import com.rating.service.app.entity.Rating;
import com.rating.service.app.exception.RatingException;
import com.rating.service.app.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRatinghandler(@RequestBody Rating rating) throws RatingException{
		Rating newRating = ratingService.createRating(rating);
		return new ResponseEntity<Rating>(newRating,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Rating>> getAllRatingshandler() throws RatingException{
		List<Rating> ratings = ratingService.getAllRating();
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingByIdHandler(@PathVariable("id") String ratingId) throws RatingException{
		Rating rating = ratingService.getRatingById(ratingId);
		return new ResponseEntity<Rating>(rating,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<List<Rating>> getRatingsByUserIdHandler(@PathVariable("id") String userId) throws RatingException{
		List<Rating> ratings = ratingService.getRatingsByUserId(userId);
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<List<Rating>> getRatingsByMovieIdHandler(@PathVariable("id") String movieId) throws RatingException{
		List<Rating> ratings = ratingService.getRatingsByMovieId(movieId);
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.ACCEPTED);
	}
}
