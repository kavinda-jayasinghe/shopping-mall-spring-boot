package com.security.sample.service;

import com.security.sample.dto.CinemaBookingDto;
import com.security.sample.dto.CinemaBookingPaymentDto;
import com.security.sample.dto.PaymentDto;
import com.security.sample.entity.CinemaBooking;
import com.security.sample.entity.Payment;
import com.security.sample.entity.PaymentStatus;
import com.security.sample.repository.MovieBookingRepo;
import com.security.sample.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private MovieBookingRepo movieBookingRepo;


    public void saveBooking(CinemaBooking cinemaBooking, long userId) {
        cinemaBooking.setUserId(userId);

        cinemaBooking.setDate(cinemaBooking.getDate());
        cinemaBooking.setTime(cinemaBooking.getTime());
        cinemaBooking.setNoOfSeats(cinemaBooking.getNoOfSeats());

        movieBookingRepo.save(cinemaBooking);

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        paymentRepo.save(payment);
    }


    }




