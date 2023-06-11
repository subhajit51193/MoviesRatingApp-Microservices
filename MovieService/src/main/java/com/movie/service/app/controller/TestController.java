package com.movie.service.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping
	public ResponseEntity<String> getString(){
		
		String s = "This is a test method";
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
	}
}
