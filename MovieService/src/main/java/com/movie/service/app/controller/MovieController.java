package com.movie.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.service.app.entity.Movie;
import com.movie.service.app.exception.MovieException;
import com.movie.service.app.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@PostMapping("/add")
	public ResponseEntity<Movie> addMovieHandler(@RequestBody Movie movie) throws MovieException{
		Movie newMovie = movieService.addMovie(movie);
		return new ResponseEntity<Movie>(newMovie,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getAllMoviesHandler() throws MovieException{
		List<Movie> movies = movieService.getAllMovies();
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieByIdHandler(@PathVariable("id") String movieId) throws MovieException{
		Movie foundMovie = movieService.getMovieById(movieId);
		return new ResponseEntity<Movie>(foundMovie,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Movie> deleteMoviehandler(@PathVariable("id") String movieId) throws MovieException{
		Movie movie = movieService.getMovieById(movieId);
		Movie deletedMovie = movieService.deleteMovie(movie);
		return new ResponseEntity<Movie>(deletedMovie,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Movie> updateMoviehandler(@PathVariable("id") String movieId, @RequestBody Movie movie) throws MovieException{
		Movie updatedMovie = movieService.updateMovie(movieId, movie);
		return new ResponseEntity<Movie>(updatedMovie,HttpStatus.ACCEPTED);
	}
}
