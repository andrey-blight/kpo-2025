package com.example.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateIncomeDto {
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("amount")
    private float amount;
}