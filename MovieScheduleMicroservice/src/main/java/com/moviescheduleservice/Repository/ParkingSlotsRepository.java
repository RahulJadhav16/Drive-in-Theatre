package com.moviescheduleservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviescheduleservice.Model.ParkingSlots;

@Repository
public interface ParkingSlotsRepository extends JpaRepository<ParkingSlots, Integer> {

	public List<ParkingSlots> findByMovieTitleAndMovieDateAndMoviePlayTime(
			String movieTitle,String movieDate, String moviePlayTime);
	
}
