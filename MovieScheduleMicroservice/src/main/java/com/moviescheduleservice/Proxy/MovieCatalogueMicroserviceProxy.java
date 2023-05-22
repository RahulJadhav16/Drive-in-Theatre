package com.moviescheduleservice.Proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="Movie-Catalogue")
public interface MovieCatalogueMicroserviceProxy {
	
	@GetMapping("/viewAllMovieTitles")
	public List<String> viewAllMovieTitles();
}
