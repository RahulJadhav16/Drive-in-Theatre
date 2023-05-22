package com.pradeep.casestudy1.MovieBookingMicroservice.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoBookingFoundException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoMovieTitleOrMovieDateOrMoviePlayTimeException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoParkingSlotAvailableException;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.Booking;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.BookingDetailsWithMovieDetails;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.MovieSchedule;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.ParkingSlots;

@Service
public interface BookingService {

	public ResponseEntity<List<Integer>> viewParkingSlotAvailability(ParkingSlots parkingSlots) throws NoMovieTitleOrMovieDateOrMoviePlayTimeException;
	
	public ResponseEntity<String> bookTicket(Booking bookingObj) throws NoMovieTitleOrMovieDateOrMoviePlayTimeException, NoParkingSlotAvailableException;
	
	public BookingDetailsWithMovieDetails viewBookingDetails( String ticketConfirmationId) throws NoBookingFoundException;
	
	public ResponseEntity<String> cancelBooking(String ticketConfirmationId) throws NoBookingFoundException;

	public ResponseEntity<List<MovieSchedule>> viewMoviesPlayingForDate(String movieDate);
}
