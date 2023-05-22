package com.moviescheduleservice.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviescheduleservice.Model.MovieSchedule;


@Repository
public interface MovieScheduleRepository extends CrudRepository<MovieSchedule,Integer>{

	
	List<MovieSchedule> findByMovieDate(String movieDate);
	
	List<MovieSchedule> findByMovieDateAndMoviePlayTime(
			String movieDate,String moviePlayTime);
	
}
