package com.moviescheduleservice.Service;
import java.util.regex.Matcher;


import java.util.regex.Pattern;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moviescheduleservice.Exception.NoMovieScheduleForDataException;
import com.moviescheduleservice.Model.MovieSchedule;
import com.moviescheduleservice.Repository.MovieScheduleRepository;

@Service
public interface MovieScheduleService {

	public ResponseEntity<String> addMovieScheduleForDate(MovieSchedule movieSchedule);
    
	public ResponseEntity<List<MovieSchedule>> getMovieScheduleForDate(String movieDate) throws NoMovieScheduleForDataException;
	
	public ResponseEntity<String> updateMovieScheduleForDate(MovieSchedule movieSchedule,String movieDate) throws NoMovieScheduleForDataException;
	
	public ResponseEntity<String> removeMovieScheduleForDate(MovieSchedule movieSchedule,String movieDate) throws NoMovieScheduleForDataException;
}
