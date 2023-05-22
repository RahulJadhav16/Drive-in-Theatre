package com.pradeep.casestudy1.MovieCatalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.casestudy1.MovieCatalogue.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	List<Movie> findByMovieTitle(String movieTitle);
}
