package com.example.payment.dto;

import lombok.Data;

@Data
public class PaymentEventDto {
    private int userId;
    private float amount;
}