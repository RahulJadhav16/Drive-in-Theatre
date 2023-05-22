	package com.moviescheduleservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviescheduleservice.Exception.NoMovieScheduleForDataException;
import com.moviescheduleservice.Model.MovieSchedule;
import com.moviescheduleservice.Repository.MovieScheduleRepository;
import com.moviescheduleservice.Service.MovieScheduleService;

@RestController
public class MovieScheduleController {
	
	  @Autowired
	  private MovieScheduleService movieScheduleService;
	  
	  @PostMapping("/addMovieSchedule")
	  public ResponseEntity<String> addMovieScheduleForDate(@RequestBody MovieSchedule movieSchedule) {
		  return movieScheduleService.addMovieScheduleForDate(movieSchedule);
	  }
	  
	  @GetMapping("/getMovieScheduleForDate/{movieDate}")
	  public ResponseEntity<List<MovieSchedule>> getMovieScheduleForDate(@PathVariable String movieDate) 
			  throws NoMovieScheduleForDataException{
		 return movieScheduleService.getMovieScheduleForDate(movieDate);
	  }
	  
	  @PutMapping("/updateMovieScheduleForDate/{movieDate}")
	  public ResponseEntity<String> updateMovieScheduleForDate(@RequestBody MovieSchedule movieSchedule,
			  @PathVariable String movieDate) throws NoMovieScheduleForDataException{
		 return movieScheduleService.updateMovieScheduleForDate(movieSchedule, movieDate);
	  }
	  
	  @DeleteMapping("/removeMovieScheduleForDate/{movieDate}")
	  public ResponseEntity<String> removeMovieScheduleForDate(@RequestBody MovieSchedule movieSchedule,
			  @PathVariable String movieDate) throws NoMovieScheduleForDataException{
		 return movieScheduleService.removeMovieScheduleForDate(movieSchedule, movieDate);
	  }
	  
	  @ExceptionHandler(NoMovieScheduleForDataException.class)
		public ResponseEntity<String> handleException(NoMovieScheduleForDataException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		}
}
