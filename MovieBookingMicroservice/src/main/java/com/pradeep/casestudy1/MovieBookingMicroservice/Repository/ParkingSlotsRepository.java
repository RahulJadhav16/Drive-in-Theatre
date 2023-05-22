package com.pradeep.casestudy1.MovieBookingMicroservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.casestudy1.MovieBookingMicroservice.model.ParkingSlots;

@Repository
public interface ParkingSlotsRepository extends JpaRepository<ParkingSlots, Integer>{
	List<ParkingSlots> findByMovieTitleAndMovieDateAndMoviePlayTimeAndIsSlotAvailable
								(String movieTitle,String movieDate,String moviePlayTime,String yes);
	
	List<ParkingSlots> findByAvailableSlotsAndMovieTitleAndMovieDateAndMoviePlayTime
								(int availableSlots,String movieTitle,String movieDate,String moviePlayTime);
}
