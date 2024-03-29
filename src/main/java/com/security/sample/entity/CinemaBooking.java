package com.security.sample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private long userId;
    private String date;
    private String time;
    private int noOfSeats;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
