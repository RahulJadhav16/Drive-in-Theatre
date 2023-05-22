
package com.pradeep.casestudy1.MovieBookingMicroservice.model;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieSchedule {
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
    @Id
    private int id;
    private String movieTitle;
    private String movieDate;
    private String moviePlayTime;
    private Double priceForFirstTwoRows;
    private Double priceForMiddleTwoRows;
    private Double priceForLastTwoRows;
	public MovieSchedule() {
		super();
	}
	public MovieSchedule(int id, String movieTitle, String movieDate, String moviePlayTime, Double priceForFirstTwoRows,
			Double priceForMiddleTwoRows, Double priceForLastTwoRows) {
		super();
		this.id = id;
		this.movieTitle = movieTitle;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
		this.priceForFirstTwoRows = priceForFirstTwoRows;
		this.priceForMiddleTwoRows = priceForMiddleTwoRows;
		this.priceForLastTwoRows = priceForLastTwoRows;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Double getPriceForFirstTwoRows() {
		return priceForFirstTwoRows;
	}
	public void setPriceForFirstTwoRows(Double priceForFirstTwoRows) {
		this.priceForFirstTwoRows = priceForFirstTwoRows;
	}
	public Double getPriceForMiddleTwoRows() {
		return priceForMiddleTwoRows;
	}
	public void setPriceForMiddleTwoRows(Double priceForMiddleTwoRows) {
		this.priceForMiddleTwoRows = priceForMiddleTwoRows;
	}
	public Double getPriceForLastTwoRows() {
		return priceForLastTwoRows;
	}
	public void setPriceForLastTwoRows(Double priceForLastTwoRows) {
		this.priceForLastTwoRows = priceForLastTwoRows;
	}
    
    

 
}
