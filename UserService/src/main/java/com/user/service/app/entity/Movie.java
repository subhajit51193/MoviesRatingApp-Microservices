package com.user.service.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Column(name = "MOVIE_ID")
	private String movieId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "RELEASE_DATE")
	private String releaseDate;
	
	@Column(name = "PLOT")
	private String plot;
	
}
