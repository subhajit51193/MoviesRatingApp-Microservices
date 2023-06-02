package com.movie.service.app.service;

import java.util.List;

import com.movie.service.app.entity.Movie;
import com.movie.service.app.exception.MovieException;

public interface MovieService {

	public Movie addMovie(Movie movie)throws MovieException;
	
	public List<Movie> getAllMovies()throws MovieException;
	
	public Movie getMovieById(String id)throws MovieException;
	
	public Movie deleteMovie(Movie movie)throws MovieException;
	
	public Movie updateMovie(String movieId,Movie movie)throws MovieException;
}
