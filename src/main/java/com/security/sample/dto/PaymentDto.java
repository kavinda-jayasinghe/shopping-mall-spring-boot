package com.security.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private long userId;
    private long categoryId;
    private String paymentDate;
    private String paymentTime;
    private double amount;
}
