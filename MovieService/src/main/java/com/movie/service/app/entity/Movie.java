package com.movie.service.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "Movies")
public class Movie {

	@Id
	@Column(name = "MOVIE_ID")
	private String movieId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "RELEASE_DATE")
	private String releaseDate;
	
	@Column(name = "PLOT")
	private String plot;
	
	
	
}
