package com.movie.service.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.service.app.entity.Movie;
import com.movie.service.app.exception.MovieException;
import com.movie.service.app.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public Movie addMovie(Movie movie) throws MovieException {
		
		String randomMovieId = UUID.randomUUID().toString();
		movie.setMovieId(randomMovieId);
		Movie newMovie = movieRepository.save(movie);
		if (newMovie != null) {
			return newMovie;
		}
		else {
			throw new MovieException("Error");
		}
	}

	@Override
	public List<Movie> getAllMovies() throws MovieException {
		
		List<Movie> movies = movieRepository.findAll();
		if (movies.isEmpty()) {
			throw new MovieException("Empty List");
		}
		else {
			return movies;
		}
	}

	@Override
	public Movie getMovieById(String id) throws MovieException {
		
		Optional<Movie> opt = movieRepository.findById(id);
		if (opt == null) {
			throw new MovieException("Not found");
		}else {
			Movie movie = opt.get();
			return movie;
		}
	}

	@Override
	public Movie deleteMovie(Movie movie) throws MovieException {
		
		if (movie == null) {
			throw new MovieException("Not found");
		}
		else {
			movieRepository.delete(movie);
			return movie;
		}
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) throws MovieException {
		
		Optional<Movie> opt = movieRepository.findById(movieId);
		if (opt.isEmpty()) {
			throw new MovieException("Not found");
		}
		else {
			Movie foundMovie = opt.get();
			foundMovie.setName(movie.getName());
			foundMovie.setReleaseDate(movie.getReleaseDate());
			return movieRepository.save(foundMovie);
		}
	}

}
