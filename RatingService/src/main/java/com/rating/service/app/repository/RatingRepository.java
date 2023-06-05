package com.rating.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.service.app.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

}
