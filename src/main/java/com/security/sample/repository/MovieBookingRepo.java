package com.security.sample.repository;

import com.security.sample.entity.CinemaBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieBookingRepo extends JpaRepository<CinemaBooking,Long> {
    @Query("SELECT SUM(b.noOfSeats) FROM CinemaBooking b WHERE b.movie.id = :movieId")
    int getTotalBookedSeatsForMovie(@Param("movieId") Long movieId);
}

