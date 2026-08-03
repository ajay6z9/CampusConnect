package com.ajay.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RideResponse {

    private Long id;

    private String source;

    private String destination;

    private LocalDateTime departureTime;

    private Integer availableSeats;

    private Double price;

    private String description;

    private String driverName;
}