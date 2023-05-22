package com.pradeep.casestudy1.MovieBookingMicroservice.model;

public class BookingDetailsWithMovieDetails {
	
	private String ticketConfirmationId;
	private Movie movieDetails;
	private String carNumber;
	private String movieDate;
	private String moviePlayTime; 
	private int parkingSlotNumber;
	
	public BookingDetailsWithMovieDetails() {
		super();
	}

	public BookingDetailsWithMovieDetails(String ticketConfirmationId, Movie movieDetails, String carNumber,
			String movieDate, String moviePlayTime, int parkingSlotNumber) {
		super();
		this.ticketConfirmationId = ticketConfirmationId;
		this.movieDetails = movieDetails;
		this.carNumber = carNumber;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
		this.parkingSlotNumber = parkingSlotNumber;
	}

	public String getTicketConfirmationId() {
		return ticketConfirmationId;
	}

	public void setTicketConfirmationId(String ticketConfirmationId) {
		this.ticketConfirmationId = ticketConfirmationId;
	}

	public Movie getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(Movie movieDetails) {
		this.movieDetails = movieDetails;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}

	public String getMoviePlayTime() {
		return moviePlayTime;
	}

	public void setMoviePlayTime(String moviePlayTime) {
		this.moviePlayTime = moviePlayTime;
	}

	public int getParkingSlotNumber() {
		return parkingSlotNumber;
	}

	public void setParkingSlotNumber(int parkingSlotNumber) {
		this.parkingSlotNumber = parkingSlotNumber;
	}

	
	
	

	
}
