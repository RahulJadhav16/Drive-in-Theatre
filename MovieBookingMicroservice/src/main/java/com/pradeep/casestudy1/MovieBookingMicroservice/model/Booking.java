package com.pradeep.casestudy1.MovieBookingMicroservice.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
	
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Id
	private int id;
	private String carNumber;
	private String movieTitle;
	private String movieDate;
	private String moviePlayTime;
	private int parkingSlotNumber;
	private String ticketConfirmationId;

	public Booking(String carNumber, String movieTitle, String movieDate, String moviePlayTime,
			int parkingSlotNumber) {
		super();
		this.carNumber = carNumber;
		this.movieTitle = movieTitle;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
		this.parkingSlotNumber = parkingSlotNumber;
	}

	public Booking() {
		super();
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTicketConfirmationId(String ticketConfirmationId) {
		this.ticketConfirmationId = ticketConfirmationId;
	}
	

}
