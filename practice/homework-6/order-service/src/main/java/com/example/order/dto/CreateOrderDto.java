package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateOrderDto {
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("amount")
    private float amount;
}
