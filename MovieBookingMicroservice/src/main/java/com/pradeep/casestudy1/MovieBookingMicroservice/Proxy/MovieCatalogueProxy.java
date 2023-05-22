package com.pradeep.casestudy1.MovieBookingMicroservice.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pradeep.casestudy1.MovieBookingMicroservice.model.Movie;

@FeignClient(name="Movie-Catalogue")
public interface MovieCatalogueProxy {

	@GetMapping("/searchMovieDetailsByTitle/{movieTitle}")
	public Movie searchMovieDetailsByTitle(@PathVariable("movieTitle") String movieTitle);
}
