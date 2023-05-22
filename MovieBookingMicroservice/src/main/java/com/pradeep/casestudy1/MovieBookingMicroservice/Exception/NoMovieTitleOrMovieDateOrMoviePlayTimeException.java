package com.pradeep.casestudy1.MovieBookingMicroservice.Exception;

public class NoMovieTitleOrMovieDateOrMoviePlayTimeException extends Exception {

	public NoMovieTitleOrMovieDateOrMoviePlayTimeException(String message) {
		super(message);
	}
}
