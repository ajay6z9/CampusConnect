package com.ajay.campusconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideRequestResponse {

    private Long requestId;

    private String passengerName;

    private String driverName;

    private String source;

    private String destination;

    private String status;

}