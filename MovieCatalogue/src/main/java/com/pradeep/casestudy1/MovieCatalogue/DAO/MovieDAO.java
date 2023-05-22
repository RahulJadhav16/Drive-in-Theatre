package com.pradeep.casestudy1.MovieCatalogue.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pradeep.casestudy1.MovieCatalogue.Exception.MovieAlreadyExistsException;
import com.pradeep.casestudy1.MovieCatalogue.Exception.NoMovieFoundException;
import com.pradeep.casestudy1.MovieCatalogue.model.Movie;
import com.pradeep.casestudy1.MovieCatalogue.repository.MovieRepository;
import com.pradeep.casestudy1.MovieCatalogue.service.MovieService;

@Component
public class MovieDAO implements MovieService{

	@Autowired
	private MovieRepository movieRepository;

	public ResponseEntity<String> addMovieDetails(Movie movieObj) throws MovieAlreadyExistsException {
		
		List<Movie> moviesList = new ArrayList<Movie>();
		List<String> movieTitles = new ArrayList<String>();
		// retrieving all the movie details from the repository
		moviesList = movieRepository.findAll();
		for(Movie movie:moviesList) {
			// adding all the movie titles into a list
			movieTitles.add(movie.getMovieTitle());
		}
		
		for(String movieTitle:movieTitles) {
			// checking if a movie already exists in the catalogue. If exists, throws exception.
			if(movieObj.getMovieTitle().equalsIgnoreCase(movieTitle)) {
				throw new MovieAlreadyExistsException(
						movieObj.getMovieTitle()+" already exists in the catalogue. Try adding another movie");
			}
		}
		// saving the movie details into the repository
		movieRepository.save(movieObj);
		return new ResponseEntity<String>(movieObj.getMovieTitle()+" is added to the Movie Catalogue.", HttpStatus.OK);
	}

	public ResponseEntity<String> updateMovieDetailsByTitle(Movie movieObj,String movieTitle) throws NoMovieFoundException{
		
		ResponseEntity<String> tempEntity = null;
		//finding movie details object by movie title
		List<Movie> moviesList = movieRepository.findByMovieTitle(movieTitle);
		//checking if there is movie object with the movietitle sent to update and throwing exception if not found
		if(moviesList.size()==0) {
			throw new NoMovieFoundException(movieTitle+" is not available in the Movie Catalogue");
		}
		Movie movie = moviesList.get(0);
		// updating the movie object if the title matches
		if(movieObj.getMovieTitle().equalsIgnoreCase(movie.getMovieTitle())) {
			movie.setImdbRating(movieObj.getImdbRating());
			movie.setMovieDescription(movieObj.getMovieDescription());
			movie.setMovieGenre(movieObj.getMovieGenre());
			movieRepository.save(movie);
			tempEntity= new ResponseEntity<String>("Updated the Movie Details", HttpStatus.OK);
		}
		return tempEntity;
		
	}

	public ResponseEntity<Movie> searchMovieDetailsByTitle(String movieTitle) throws NoMovieFoundException {
		//finding movie details object by movie title
		List<Movie> moviesList=movieRepository.findByMovieTitle(movieTitle);
		//checking if there is movie object with the movietitle sent to update and throwing exception if not found
		if(moviesList.size()==0) {
			throw new NoMovieFoundException(movieTitle+" is not available in the Movie Catalogue");
		}
		 return new ResponseEntity<Movie>(moviesList.get(0),HttpStatus.OK);
	}

	public ResponseEntity<String> deleteMovieDetailsByTitle(String movieTitle) throws NoMovieFoundException {
		//finding movie details object by movie title
		List<Movie> moviesList=movieRepository.findByMovieTitle(movieTitle);
		//checking if there is movie object with the movietitle sent to update and throwing exception if not found
		if(moviesList.size()==0) {
			throw new NoMovieFoundException(movieTitle+" is not available in the Movie Catalogue");
		}
		Movie movie = moviesList.get(0);
		//deleting the movie details object from the repository
		movieRepository.delete(movie);
		return new ResponseEntity<String>("Deleted the Movie Details from the catalogue", HttpStatus.OK);
		
	}

	public List<String> viewAllMovieTitles() {
		
		List<Movie> moviesList = new ArrayList<Movie>();
		List<String> movieTitles = new ArrayList<String>();
		// finding all movie objects from the repository and storing in a list
		moviesList = movieRepository.findAll();
		for(Movie movie:moviesList) {
			// adding only the movie titles of the movie details found and storing in a list
			movieTitles.add(movie.getMovieTitle());
		}
		// returning the list of movie titles
		return movieTitles;
	}
}
