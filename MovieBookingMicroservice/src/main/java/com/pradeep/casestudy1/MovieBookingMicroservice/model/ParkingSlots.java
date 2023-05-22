package com.pradeep.casestudy1.MovieBookingMicroservice.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingSlots {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Id
	private int id;
	private String movieTitle;
	private String movieDate;
	private String moviePlayTime;
	private int availableSlots;
	private String isSlotAvailable;

	public ParkingSlots(String movieTitle, String movieDate, String moviePlayTime) {
		super();
		this.movieTitle = movieTitle;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
	}

	public ParkingSlots() {
		super();
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
	public int getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsSlotAvailable() {
		return isSlotAvailable;
	}

	public void setIsSlotAvailable(String isSlotAvailable) {
		this.isSlotAvailable = isSlotAvailable;
	}
	
	
}
