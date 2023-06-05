package com.rating.service.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.service.app.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	public List<Rating> findByUserId(String userId);
	
	public List<Rating> findByMovieId(String movieId);
}
