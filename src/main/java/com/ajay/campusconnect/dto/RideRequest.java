package com.ajay.campusconnect.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RideRequest {

    @NotBlank
    private String source;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDateTime departureTime;

    @Min(1)
    private Integer availableSeats;

    @Positive
    private Double price;

    private String description;
}