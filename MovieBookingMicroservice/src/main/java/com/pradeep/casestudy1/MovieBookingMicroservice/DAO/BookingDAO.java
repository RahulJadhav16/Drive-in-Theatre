package com.pradeep.casestudy1.MovieBookingMicroservice.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoBookingFoundException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoMovieTitleOrMovieDateOrMoviePlayTimeException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Exception.NoParkingSlotAvailableException;
import com.pradeep.casestudy1.MovieBookingMicroservice.Proxy.MovieCatalogueProxy;
import com.pradeep.casestudy1.MovieBookingMicroservice.Proxy.MovieScheduleProxy;
import com.pradeep.casestudy1.MovieBookingMicroservice.Repository.BookingRepository;
import com.pradeep.casestudy1.MovieBookingMicroservice.Repository.ParkingSlotsRepository;
import com.pradeep.casestudy1.MovieBookingMicroservice.Service.BookingService;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.Booking;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.BookingDetailsWithMovieDetails;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.Movie;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.MovieSchedule;
import com.pradeep.casestudy1.MovieBookingMicroservice.model.ParkingSlots;

@Component
public class BookingDAO implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ParkingSlotsRepository parkingSlotsRepository;
	
	@Autowired
	private MovieCatalogueProxy movieCatalogueProxy;
	
	@Autowired
	private MovieScheduleProxy movieScheduleProxy;
	
	@Override
	public ResponseEntity<List<MovieSchedule>> viewMoviesPlayingForDate(String movieDate) {
		// getting all the movies scheduled for a date from the movie schedule proxy
		return movieScheduleProxy.getMovieScheduleForDate(movieDate);
	}

	@Override
	public ResponseEntity<List<Integer>> viewParkingSlotAvailability(ParkingSlots parkingSlots) 
					throws NoMovieTitleOrMovieDateOrMoviePlayTimeException {
		
		List<Integer> availableSlots = new ArrayList<Integer>();
		// getting all the parking slots objects of not booked slots
		List<ParkingSlots> parkingSlotsList= parkingSlotsRepository.
				findByMovieTitleAndMovieDateAndMoviePlayTimeAndIsSlotAvailable
				(parkingSlots.getMovieTitle(),parkingSlots.getMovieDate(), parkingSlots.getMoviePlayTime(),"YES");
		
		// exception if no parking slots are found 
		if(parkingSlotsList.size()==0) {
			throw new NoMovieTitleOrMovieDateOrMoviePlayTimeException(
					"MovieTitle or MovieDate or MoviePlayTime is not correct. Check the schedule and try booking again");
			
		}
		
		// storing all the available parking slot numbers into a list
		for(ParkingSlots slots: parkingSlotsList) {
			availableSlots.add(slots.getAvailableSlots()) ;
		}
		
		return new ResponseEntity<List<Integer>>(availableSlots,HttpStatus.FOUND);
		
	}

	@Override
	public ResponseEntity<String> bookTicket(Booking bookingObj) 
			throws NoMovieTitleOrMovieDateOrMoviePlayTimeException,NoParkingSlotAvailableException {
		
		// getting all the parking slots objects of not booked slots
		List<ParkingSlots> parkingSlotsList = parkingSlotsRepository.
				findByMovieTitleAndMovieDateAndMoviePlayTimeAndIsSlotAvailable
				(bookingObj.getMovieTitle(),bookingObj.getMovieDate(), bookingObj.getMoviePlayTime(),"YES");
		
		// exception if no parking slots are found 
		if(parkingSlotsList.size()==0) {
			throw new NoMovieTitleOrMovieDateOrMoviePlayTimeException(
					"MovieTitle or MovieDate or MoviePlayTime is not correct. Check the schedule and try booking again");
			
		}
		
		for(ParkingSlots slots: parkingSlotsList) {
			// checking if the slot number which user want to book is available and not booked already
			if(slots.getAvailableSlots()==bookingObj.getParkingSlotNumber()
					&&slots.getIsSlotAvailable().equalsIgnoreCase("YES")) {
				
				// generating a random unique alphanumeric id for ticket confirmation id 
				UUID uuid = UUID.randomUUID();
				String uniqueId = uuid.toString().substring(0,8);
				
				// setting the ticket confirmation id into the booking object
				bookingObj.setTicketConfirmationId(uniqueId);
				
				// setting the parking slot availability status to NO.
				slots.setIsSlotAvailable("NO");
				
				// saving the booking object into the DB
				bookingRepository.save(bookingObj);
				
				parkingSlotsRepository.save(slots);
				return new ResponseEntity<String>("The slot is booked."+" Your Ticket Confirmation Id: "+uniqueId, HttpStatus.OK) ;
			}
		}
		
		throw new NoParkingSlotAvailableException("The parking slot "+bookingObj.getParkingSlotNumber()+" is either booked or not available.");
		
	}

	@Override
	public BookingDetailsWithMovieDetails viewBookingDetails(String ticketConfirmationId) throws NoBookingFoundException {
		
		// finding the booking object by ticketConfirmationId
		List<Booking> bookingsList= bookingRepository.findByTicketConfirmationId(ticketConfirmationId);
		// throwing exception if no booking object found with the given ticketConfirmationId
		if(bookingsList.size()==0) {
			throw new NoBookingFoundException("No booking found with "+ticketConfirmationId);
		}
		// getting the movie details object by movie title from the movie catalog proxy
		Movie movie = movieCatalogueProxy.searchMovieDetailsByTitle(bookingsList.get(0).getMovieTitle());
		
		Booking bookingDetails = new Booking();
		
		bookingDetails=bookingsList.get(0);
		
		/*
		 * created a new class and added Movie object(from movie catalog proxy) attribute to return the
		 * movie details object(from movie catalog proxy) along with the booking details object
		 */
		return new BookingDetailsWithMovieDetails(
				ticketConfirmationId, new Movie(movie.getMovieTitle(), movie.getMovieDescription(), movie.getMovieGenre(),movie.getImdbRating())
				,bookingDetails.getCarNumber(),bookingDetails.getMovieDate(), bookingDetails.getMoviePlayTime(),bookingDetails.getParkingSlotNumber());
	}

	@Override
	public ResponseEntity<String> cancelBooking(String ticketConfirmationId) throws NoBookingFoundException {
		
		// finding the booking object by ticketConfirmationId
		List<Booking> bookingsList= bookingRepository.findByTicketConfirmationId(ticketConfirmationId);
		
		// throwing exception if no booking object found with the given ticketConfirmationId
		if(bookingsList.size()==0) {
			throw new NoBookingFoundException("No booking found with "+ticketConfirmationId);
		}
		
		for(Booking bookingDetails : bookingsList) {
			
			// getting the parking slot object to set the slot availability status back to YES after cancelling the ticket
			List<ParkingSlots> parkingSlotsList= parkingSlotsRepository.findByAvailableSlotsAndMovieTitleAndMovieDateAndMoviePlayTime(
								bookingDetails.getParkingSlotNumber(),bookingDetails.getMovieTitle()
								,bookingDetails.getMovieDate(),bookingDetails.getMoviePlayTime());
			
			ParkingSlots parkingSlotsObj= parkingSlotsList.get(0);
			// setting the slot availability status back to yes in the DB
			parkingSlotsObj.setIsSlotAvailable("YES");
			
			// saving the updated parking slots object in th DB
			parkingSlotsRepository.save(parkingSlotsObj);
			// deleting the booking details object from the DB
			bookingRepository.delete(bookingDetails);
			
		}
		return new ResponseEntity<String>("Your ticket is cancelled", HttpStatus.OK);
	}

}
