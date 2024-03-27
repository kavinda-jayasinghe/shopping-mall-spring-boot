package com.security.sample.repository;

import com.security.sample.entity.CinemaBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaBookingRepo extends JpaRepository<CinemaBooking,Long> {
}

