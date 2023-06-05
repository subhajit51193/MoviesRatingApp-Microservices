package com.rating.service.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.rating.service.app.entity.Rating;
import com.rating.service.app.exception.RatingException;
import com.rating.service.app.repository.RatingRepository;
import com.rating.service.app.service.RatingService;

@SpringBootTest
@RunWith(SpringRunner.class)
class RatingServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	
	@MockBean
	private RatingRepository ratingRepository;
	
	@Test
	void createRatingTest() throws RatingException {
		
		Rating rating = new Rating();
		rating.setRatingId("1234");
		rating.setUserId("1234");
		rating.setMovieId("1234");
		rating.setRating(5);
		rating.setFeedback("Demo feedback");
		when(ratingRepository.save(rating)).thenReturn(rating);
		assertEquals(rating, ratingService.createRating(rating));
	}
	
	@Test
	void createRatingWithExceptiontest() {
		
		Rating rating = new Rating();
		rating.setRatingId("1234");
		rating.setUserId("1234");
		rating.setMovieId("1234");
		rating.setRating(5);
		rating.setFeedback("Demo feedback");
		when(ratingRepository.save(rating)).thenReturn(null);
		assertThrows(RatingException.class,
				() ->{
					ratingService.createRating(rating);
				});
	}
	@Test
	void getAllRatingsTest() throws RatingException {
		
		Rating rating1 = new Rating();
		rating1.setRatingId("1234");
		rating1.setUserId("1234");
		rating1.setMovieId("1234");
		rating1.setRating(5);
		rating1.setFeedback("Demo feedback");
		
		Rating rating2 = new Rating();
		rating2.setRatingId("123");
		rating2.setUserId("123");
		rating2.setMovieId("123");
		rating2.setRating(5);
		rating2.setFeedback("Demo feedback");
		
		when(ratingRepository.findAll()).thenReturn(Stream
				.of(rating1,rating2).collect(Collectors.toList()));
		assertEquals(2, ratingService.getAllRating().size());
	}
	
	@Test
	void getAllRatingsWithExceptionTest() {
		
		when(ratingRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(RatingException.class, 
				() ->{
					ratingService.getAllRating();
				});
	}
	
	@Test
	void getRatingByIdTest() throws RatingException {
	
		Rating rating = new Rating();
		rating.setRatingId("1234");
		rating.setUserId("1234");
		rating.setMovieId("1234");
		rating.setRating(5);
		rating.setFeedback("Demo feedback");
		when(ratingRepository.findById(anyString())).thenReturn(Optional.of(rating));
		assertEquals(Optional.of(rating).get(), ratingService.getRatingById(anyString()));
	}
	
	@Test
	void getRatingByIdWithExceptionTest() {
		
		when(ratingRepository.findById(anyString())).thenReturn(null);
		assertThrows(RatingException.class, 
				() ->{
					ratingService.getRatingById(anyString());
				});
	}
	
	@Test
	void getRatingsByUserIdTest() throws RatingException{
		
		Rating rating1 = new Rating();
		rating1.setRatingId("1234");
		rating1.setUserId("1234");
		rating1.setMovieId("1234");
		rating1.setRating(5);
		rating1.setFeedback("Demo feedback");
		
		Rating rating2 = new Rating();
		rating2.setRatingId("123");
		rating2.setUserId("123");
		rating2.setMovieId("123");
		rating2.setRating(5);
		rating2.setFeedback("Demo feedback");
		when(ratingRepository.findByUserId(anyString())).thenReturn(Stream
				.of(rating1,rating2).collect(Collectors.toList()));
		assertEquals(2, ratingService.getRatingsByUserId(anyString()).size());
	}
	
	@Test
	void getRatingsByUserIdWithExceptionTest() {
		
		when(ratingRepository.findByUserId(anyString())).thenReturn(Collections.emptyList());
		assertThrows(RatingException.class, 
				() ->{
					ratingService.getRatingsByUserId(anyString());
				});
	}
	
	@Test
	void getRatingsByMovieIdTest() throws RatingException {
		
		Rating rating1 = new Rating();
		rating1.setRatingId("1234");
		rating1.setUserId("1234");
		rating1.setMovieId("1234");
		rating1.setRating(5);
		rating1.setFeedback("Demo feedback");
		
		Rating rating2 = new Rating();
		rating2.setRatingId("123");
		rating2.setUserId("123");
		rating2.setMovieId("123");
		rating2.setRating(5);
		when(ratingRepository.findByMovieId(anyString())).thenReturn(Stream
				.of(rating1,rating2).collect(Collectors.toList()));
		assertEquals(2, ratingService.getRatingsByMovieId(anyString()).size());
	}
	
	@Test
	void getRatingsByMovieIdWithExceptionTest() {
		
		when(ratingRepository.findByMovieId(anyString())).thenReturn(Collections.emptyList());
		assertThrows(RatingException.class, 
				() ->{
					ratingService.getRatingsByMovieId(anyString());
				});
	}

}
