package com.security.sample.service;


import com.security.sample.dto.CinemaDto;
import com.security.sample.entity.Cinema;
import com.security.sample.repository.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepo cinemaRepo;

    public Cinema postMovie(Cinema cinema) {
        return cinemaRepo.save(cinema);

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

    public String deleteMovie(long id) {
        if (cinemaRepo.existsById(id)) {
            cinemaRepo.deleteById(id);
            return "Movie with id " + id + " has been deleted successfully.";
        } else {
            return "Movie with id " + id + " does not exist.";
        }
    }

    public List<Cinema> getAllMovies() {
        return cinemaRepo.findAll();
    }

    public List<Cinema> searchMoviesByName(String name) {
        return cinemaRepo.findByFilmNameContainingIgnoreCase(name);
    }
}