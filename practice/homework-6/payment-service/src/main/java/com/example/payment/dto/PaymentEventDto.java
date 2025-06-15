package com.example.payment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentEventDto {
    private UUID orderId;
    private int userId;
    private float amount;
}