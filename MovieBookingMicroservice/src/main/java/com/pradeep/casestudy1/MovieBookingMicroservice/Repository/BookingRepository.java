package com.pradeep.casestudy1.MovieBookingMicroservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.casestudy1.MovieBookingMicroservice.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findByTicketConfirmationId(String ticketConfirmationId);
}
