package com.security.sample.controller;


import com.security.sample.dto.CinemaBookingPaymentDto;
import com.security.sample.entity.CinemaBooking;
import com.security.sample.entity.Movie;
import com.security.sample.entity.Feedback;
import com.security.sample.entity.Payment;
import com.security.sample.service.BookingService;
import com.security.sample.service.MovieService;
import com.security.sample.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authenticated-user")
@CrossOrigin(origins = "http://localhost:4200")

public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private BookingService bookingService;

    //--------------------------------------------CINEMA THEATER-------------------------------------
    //get all movies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }


    //get by name
    @GetMapping("/search-by-name/{name}")
    public ResponseEntity<List<Movie>> getByName(@PathVariable String name) {
        List<Movie> movies = movieService.searchMoviesByName(name);

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }

    //--------------------------------------------------------------FEEDBACK

    //add feed back
    @PostMapping("/feed-back/{userId}/{categoryId}")
    public Feedback addFeedbackForItem(@PathVariable long userId, @PathVariable long categoryId, @RequestBody Feedback feedback) {
        return feedbackService.addFeedback(userId, categoryId, feedback);
    }
    //get all feed back

    @GetMapping("/get-all-feed-back")
    public List<Object[]> getAllFeedbackWithUserNames() {
        return feedbackService.getAllFeedbackWithUserName();
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }

    @PutMapping("/{feedbackId}")
    public Feedback updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback updatedFeedback) {
        return feedbackService.updateFeedback(feedbackId, updatedFeedback);
    }

//--------------------------------------------------------------Booking


    @PostMapping(path = "/movie-booking/{userId}")
    public ResponseEntity<String> movieBooking(
            @PathVariable long userId,
            @RequestBody CinemaBooking cinemaBooking) {
        bookingService.saveBooking(cinemaBooking,userId);
        return ResponseEntity.status(HttpStatus.OK).body("Data saved successfully");
    }

//--------------------------------------------------------------payment


}


