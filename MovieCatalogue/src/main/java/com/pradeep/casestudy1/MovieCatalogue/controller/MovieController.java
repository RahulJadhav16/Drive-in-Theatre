package com.pradeep.casestudy1.MovieCatalogue.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.casestudy1.MovieCatalogue.Exception.MovieAlreadyExistsException;
import com.pradeep.casestudy1.MovieCatalogue.Exception.NoMovieFoundException;
import com.pradeep.casestudy1.MovieCatalogue.model.Movie;
import com.pradeep.casestudy1.MovieCatalogue.service.MovieService;

@RestController
public class MovieController {
	
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/addMovieDetails")
	public ResponseEntity<String> addMovieDetails(@RequestBody Movie movieObj) throws MovieAlreadyExistsException {
		return movieService.addMovieDetails(movieObj);
	}
	
	@PutMapping("/updateMovieDetailsByTitle/{movieTitle}")
	public ResponseEntity<String> updateMovieDetailsByTitle(@RequestBody Movie movieObj,
			@PathVariable("movieTitle") String movieTitle) throws NoMovieFoundException{
		return movieService.updateMovieDetailsByTitle(movieObj, movieTitle);
	}
	
	@GetMapping("/searchMovieDetailsByTitle/{movieTitle}")
	public ResponseEntity<Movie> searchMovieDetailsByTitle(@PathVariable("movieTitle") String movieTitle) 
			throws NoMovieFoundException {
		return movieService.searchMovieDetailsByTitle(movieTitle);
	}
	
	@DeleteMapping("/deleteMovieDetailsByTitle/{movieTitle}")
	public ResponseEntity<String> deleteMovieDetailsByTitle(@PathVariable("movieTitle") String movieTitle) throws NoMovieFoundException {
		return movieService.deleteMovieDetailsByTitle(movieTitle);
	}
	
	@GetMapping("/viewAllMovieTitles")
	public List<String> viewAllMovieTitles() {
		return movieService.viewAllMovieTitles();
	}
	
	@ExceptionHandler(NoMovieFoundException.class)
	public ResponseEntity<String> handleException1(NoMovieFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MovieAlreadyExistsException.class)
	public ResponseEntity<String> handleException2(MovieAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
