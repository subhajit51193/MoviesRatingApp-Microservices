package com.movie.service.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.service.app.entity.Movie;
import com.movie.service.app.exception.MovieException;
import com.movie.service.app.repository.MovieRepository;
import com.movie.service.app.service.MovieService;

@SpringBootTest
@RunWith(SpringRunner.class)
class MovieServiceApplicationTests {

	@Autowired
	private MovieService movieService;
	
	@MockBean
	private MovieRepository movieRepository;
	
	@Test
	void addMovieTest() throws MovieException {
		Movie movie = new Movie();
		movie.setMovieId("1234");
		movie.setName("Demo");
		movie.setReleaseDate("01-01-2002");
		
		when(movieRepository.save(movie)).thenReturn(movie);
		assertEquals(movie, movieService.addMovie(movie));
		
	}
	
	@Test
	void addMovieWithExceptionTest() {
		
		Movie movie = new Movie();
		movie.setMovieId("1234");
		movie.setName("Demo");
		movie.setReleaseDate("01-01-2002");
		
		when(movieRepository.save(movie)).thenReturn(null);
		assertThrows(MovieException.class,
				() ->{
					movieService.addMovie(movie);
				});
	}
	
	@Test
	void getAllMoviesTest() throws MovieException {
		
		Movie movie1 = new Movie();
		movie1.setMovieId("1234");
		movie1.setName("Demo");
		movie1.setReleaseDate("01-01-2002");
		
		Movie movie2 = new Movie();
		movie2.setMovieId("12345");
		movie2.setName("Demo2");
		movie2.setReleaseDate("02-01-2002");
		
		when(movieRepository.findAll()).thenReturn(Stream
				.of(movie1,movie2).collect(Collectors.toList()));
		assertEquals(2, movieService.getAllMovies().size());
	}
	
	@Test
	void getAllMoviesWithExceptionTest() {
		
		when(movieRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(MovieException.class, 
				() ->{
					movieService.getAllMovies();
				});
	}
	
	@Test
	void getMovieByIdTest() throws MovieException {
		
		Movie movie = new Movie();
		movie.setMovieId("1234");
		movie.setName("Demo");
		movie.setReleaseDate("01-01-2002");
		
		when(movieRepository.findById(anyString())).thenReturn(Optional.of(movie));
		assertEquals(Optional.of(movie).get(), movieService.getMovieById(anyString()));
	}
	@Test
	void getMovieByIdWithExceptionTest() {
		
		when(movieRepository.findById(anyString())).thenReturn(null);
		assertThrows(MovieException.class, 
				() ->{
					movieService.getMovieById(anyString());
				});
	}
	@Test
	void deleteMovieTest() throws MovieException {
		
		Movie movie = new Movie();
		movie.setMovieId("1234");
		movie.setName("Demo");
		movie.setReleaseDate("01-01-2002");
		
		movieService.deleteMovie(movie);
		verify(movieRepository,times(1)).delete(movie);
	}

}
