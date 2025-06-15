package com.example.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAccountDto {
    @JsonProperty("user_id")
    private int userId;
}
