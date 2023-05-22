package com.pradeep.casestudy1.MovieBookingMicroservice.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoBookingFoundException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoMovieTitleOrMovieDateOrMoviePlayTimeException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoParkingSlotAvailableException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Service.BookingService;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.Booking;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.BookingDetailsWithMovieDetails;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.MovieSchedule;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.ParkingSlots;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/viewMoviesPlayingForDate/{movieDate}")
	public ResponseEntity<List<MovieSchedule>> viewMoviesPlayingForDate(@PathVariable String movieDate){
		return bookingService.viewMoviesPlayingForDate(movieDate);
	}
	@PostMapping("/viewParkingSlotAvailability")
	public ResponseEntity<List<Integer>> viewParkingSlotAvailability(@RequestBody ParkingSlots parkingSlots) 
			throws NoMovieTitleOrMovieDateOrMoviePlayTimeException{
		return bookingService.viewParkingSlotAvailability(parkingSlots);
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<String> bookTicket(@RequestBody Booking bookingObj)
			throws NoMovieTitleOrMovieDateOrMoviePlayTimeException, NoParkingSlotAvailableException {
		return bookingService.bookTicket(bookingObj);
	}
	
	@GetMapping("/viewBookingDetails/{ticketConfirmationId}")
	public BookingDetailsWithMovieDetails viewBookingDetails(@PathVariable String ticketConfirmationId) throws NoBookingFoundException {
		return bookingService.viewBookingDetails(ticketConfirmationId);
	}
	
	@GetMapping("/cancelBooking/{ticketConfirmationId}")
	public ResponseEntity<String> cancelBooking(@PathVariable String ticketConfirmationId) throws NoBookingFoundException {
		return bookingService.cancelBooking(ticketConfirmationId);
	}
	
	@ExceptionHandler(NoMovieTitleOrMovieDateOrMoviePlayTimeException.class)
	public ResponseEntity<String> handleException1(NoMovieTitleOrMovieDateOrMoviePlayTimeException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoParkingSlotAvailableException.class)
	public ResponseEntity<String> handleException2(NoParkingSlotAvailableException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoBookingFoundException.class)
	public ResponseEntity<String> handleException3(NoBookingFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}
