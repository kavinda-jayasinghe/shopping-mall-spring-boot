package com.security.sample.service;


import com.security.sample.dto.CinemaDto;
import com.security.sample.entity.Movie;
import com.security.sample.repository.MovieBookingRepo;
import com.security.sample.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo cinemaRepo;
    @Autowired
    private MovieBookingRepo movieBookingRepo;

    public Movie postMovie(Movie movie) {
        return cinemaRepo.save(movie);

    }

    //update
    public String updateMovie(CinemaDto cinemaDto, long id) {
        try {
            if (cinemaRepo.existsById(id)) {
                cinemaRepo.updateMovie(
                        cinemaDto.getFilmName(),
                        cinemaDto.getDescription(),
                        cinemaDto.getShowTime(),
                        cinemaDto.getImg(),
                        cinemaDto.getDuration(),
                        cinemaDto.getBookedSeats(),
                        cinemaDto.getDate(),
                        cinemaDto.getTime(),
                        id
                );
                return "Movie has been updated with id: " + id;
            } else {
                return "Movie does not exist for this id...";
            }
        } catch (Exception e) {
            return "An error occurred while updating the movie: " + e.getMessage();
        }
    }

//delete movie
    public String deleteMovie(long id) {
        if (cinemaRepo.existsById(id)) {
            cinemaRepo.deleteById(id);
            return "Movie with id " + id + " has been deleted successfully.";
        } else {
            return "Movie with id " + id + " does not exist.";
        }
    }
//get ALll movies
    public List<Movie> getAllMovies() {

        return cinemaRepo.findAll();
    }

//get movie by name
    public List<Movie> searchMoviesByName(String name) {
        return cinemaRepo.findByFilmNameContainingIgnoreCase(name);
    }

    //get movie by id


    //counting available seats
    public int getAvailableSeats(Long movieId) {
        Movie movie = cinemaRepo.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        int totalSeats = movie.getSeats();
        int bookedSeats = movieBookingRepo.getTotalBookedSeatsForMovie(movieId);
        return totalSeats - bookedSeats;
    }

    public Movie GetMoviebyId(long id) {
        Optional<Movie> movie = cinemaRepo.findById(id);
        return movie.orElse(null);
    }
}