package com.user.service.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	@Column(name = "RATING_ID")
	private String ratingId;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "MOVIE_ID")
	private String movieId;
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "FEEDBACK")
	private String feedback;
	
	
	private Movie movie;
}
