package com.pradeep.casestudy1.MovieBookingMicroservice.model;





public class Movie {

	private int id;
	private String movieTitle;
	private String movieDescription;
	private String movieGenre;
	private double imdbRating;
	
	public Movie(String movieTitle, String movieDescription, String movieGenre, double imdbRating) {
		super();
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.movieGenre = movieGenre;
		this.imdbRating = imdbRating;
	}

	public Movie() {
		super();
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
