package com.security.sample.service;

import com.security.sample.dto.CinemaBookingDto;
import com.security.sample.dto.CinemaBookingPaymentDto;
import com.security.sample.dto.PaymentDto;
import com.security.sample.entity.CinemaBooking;
import com.security.sample.entity.Payment;
import com.security.sample.repository.CinemaBookingRepo;
import com.security.sample.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CinemaBookingRepo cinemaBookingRepo;

    public void saveBookingAndPayment(CinemaBookingPaymentDto cinemaBookingPaymentDto) {
        PaymentDto paymentDto = cinemaBookingPaymentDto.getPaymentDto();
        CinemaBookingDto cinemaBookingDto = cinemaBookingPaymentDto.getCinemaBookingDto();

        Payment payment = new Payment();
        payment.setUserId(paymentDto.getUserId());
        payment.setCategoryId(paymentDto.getCategoryId());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setPaymentTime(paymentDto.getPaymentTime());
        payment.setAmount(paymentDto.getAmount());
        paymentRepo.save(payment);

        CinemaBooking cinemaBooking = new CinemaBooking();
        cinemaBooking.setUserId(cinemaBookingDto.getUserId());
        cinemaBooking.setCategoryId(cinemaBookingDto.getCategoryId());
        cinemaBooking.setDate(cinemaBookingDto.getDate());
        cinemaBooking.setTime(cinemaBookingDto.getTime());
        cinemaBooking.setNoOfSeats(cinemaBookingDto.getNoOfSeats());
        cinemaBookingRepo.save(cinemaBooking);
    }
}
