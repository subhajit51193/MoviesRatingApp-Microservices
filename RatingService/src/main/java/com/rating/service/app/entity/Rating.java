package com.rating.service.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ratings")
public class Rating {

	@Id
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
}
