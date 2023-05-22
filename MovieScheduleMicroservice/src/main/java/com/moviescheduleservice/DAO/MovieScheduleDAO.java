package com.moviescheduleservice.DAO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.moviescheduleservice.Exception.NoMovieScheduleForDataException;
import com.moviescheduleservice.Model.MovieSchedule;
import com.moviescheduleservice.Model.ParkingSlots;
import com.moviescheduleservice.Proxy.MovieCatalogueMicroserviceProxy;
import com.moviescheduleservice.Repository.MovieScheduleRepository;
import com.moviescheduleservice.Repository.ParkingSlotsRepository;
import com.moviescheduleservice.Service.MovieScheduleService;

@Component
public class MovieScheduleDAO implements MovieScheduleService{
	
	@Autowired
	private MovieScheduleRepository movieScheduleRepository;
	
	@Autowired
	private MovieCatalogueMicroserviceProxy movieCatalogueMicroserviceProxy;

	@Autowired
	private ParkingSlotsRepository parkingSlotsRepository;
	@Override
	public ResponseEntity<String> addMovieScheduleForDate(MovieSchedule movieSchedule) {
		// getting movie titles present in Movie Catalogue proxy
		List<String> movieTitlesList = movieCatalogueMicroserviceProxy.viewAllMovieTitles();
		
		// checking if the movie title is present in movie catalogue or not
		for(String movieTitle : movieTitlesList) {
			if(movieTitle.equals(movieSchedule.getMovieTitle())){
				// filling parking slots in parking slots table
				for(int i=1;i<=60;i++) {
					ParkingSlots parkingSlots = new ParkingSlots();
					parkingSlots.setMovieDate(movieSchedule.getMovieDate());
					parkingSlots.setMoviePlayTime(movieSchedule.getMoviePlayTime());
					parkingSlots.setMovieTitle(movieSchedule.getMovieTitle());
					parkingSlots.setAvailableSlots(i);
					parkingSlots.setIsSlotAvailable("YES");
					parkingSlotsRepository.save(parkingSlots);
				}
				// saving the moving schedule into the DB 
				movieScheduleRepository.save(movieSchedule);
				return new ResponseEntity<String>("Movie Scheduled.",HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>(
				"The movie you are trying to schedule is not present in the Movie Catalogue.",
				HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<MovieSchedule>> getMovieScheduleForDate(String movieDate) 
			throws NoMovieScheduleForDataException{
		// getting all the movie schedules for a date
		List<MovieSchedule> movieScheduleList = movieScheduleRepository.findByMovieDate(movieDate);
		if(movieScheduleList.size()==0) {
			// exception if there is no movie scheduled for a date
			throw new NoMovieScheduleForDataException("No movie schedule found for "+movieDate);
		}
		return new ResponseEntity<List<MovieSchedule>>(movieScheduleList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateMovieScheduleForDate(MovieSchedule movieSchedule,String movieDate) 
			throws NoMovieScheduleForDataException{
		// getting all the movie schedules for a date and playtime
		List<MovieSchedule> movieScheduleList=movieScheduleRepository.findByMovieDateAndMoviePlayTime(
				movieDate, movieSchedule.getMoviePlayTime());
		if(movieScheduleList.size()==0) {
			// exception if there is no movie scheduled for a date
			throw new NoMovieScheduleForDataException(
					"No movie schedule found at "+movieSchedule.getMoviePlayTime()+" on "+movieDate);
		}
		// getting the id of the found movie schedule
		int id = movieScheduleList.get(0).getId();
		// setting the id of found movie schedule into to movie schedule object to be updated 
		movieSchedule.setId(id);
		// updating the movie schedule in the DB
		movieScheduleRepository.save(movieSchedule);
		return new ResponseEntity<String>("Updated the movie schedule.",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removeMovieScheduleForDate(MovieSchedule movieSchedule,String movieDate)
			throws NoMovieScheduleForDataException{
		// getting all the movie schedules for a date and playtime
		List<MovieSchedule> movieScheduleList=movieScheduleRepository.findByMovieDateAndMoviePlayTime(
				movieDate, movieSchedule.getMoviePlayTime());
		// exception if there is no movie scheduled for a date
		if(movieScheduleList.size()==0) {
			throw new NoMovieScheduleForDataException(
					"No movie schedule found at "+movieSchedule.getMoviePlayTime()+" on "+movieDate);
		}
		
		// getting all the parking slots for the movie scheduled
		List<ParkingSlots> parkingSlotsList= parkingSlotsRepository.findByMovieTitleAndMovieDateAndMoviePlayTime(
				movieSchedule.getMovieTitle(), movieDate, movieSchedule.getMoviePlayTime());
		
		// deleting the parking slots from parking slots DB of the movie schedule
		for(ParkingSlots parkingSlot : parkingSlotsList) {
			parkingSlotsRepository.delete(parkingSlot);
		}
		
		// deleting the movie schedule from the DB
		movieScheduleRepository.delete(movieScheduleList.get(0));
		return new ResponseEntity<String>("Removed the movie schedule.",HttpStatus.OK);
	}
	
	

}
