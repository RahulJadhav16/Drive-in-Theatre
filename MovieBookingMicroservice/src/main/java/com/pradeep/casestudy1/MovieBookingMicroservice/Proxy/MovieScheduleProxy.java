package com.pradeep.casestudy1.MovieBookingMicroservice.Proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pradeep.casestudy1.MovieBookingMicroservice.model.MovieSchedule;

@FeignClient(name = "Movie-Schedule-Microservice")
public interface MovieScheduleProxy {

	@GetMapping("/getMovieScheduleForDate/{movieDate}")
	  public ResponseEntity<List<MovieSchedule>> getMovieScheduleForDate(@PathVariable String movieDate);
}
