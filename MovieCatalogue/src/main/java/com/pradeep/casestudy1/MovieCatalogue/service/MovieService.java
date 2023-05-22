package com.pradeep.casestudy1.MovieCatalogue.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pradeep.casestudy1.MovieCatalogue.Exception.MovieAlreadyExistsException;
import com.pradeep.casestudy1.MovieCatalogue.Exception.NoMovieFoundException;
import com.pradeep.casestudy1.MovieCatalogue.model.Movie;


@Service
public interface MovieService {
	

	public ResponseEntity<String> addMovieDetails(Movie movieObj) throws MovieAlreadyExistsException;

	public ResponseEntity<String> updateMovieDetailsByTitle(Movie movieObj,String movieTitle) throws NoMovieFoundException;

	public ResponseEntity<Movie> searchMovieDetailsByTitle(String movieTitle) throws NoMovieFoundException;

	public ResponseEntity<String> deleteMovieDetailsByTitle(String movieTitle) throws NoMovieFoundException;

	public List<String> viewAllMovieTitles();

}
