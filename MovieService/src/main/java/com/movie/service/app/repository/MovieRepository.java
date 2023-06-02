package com.movie.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.service.app.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{

}
