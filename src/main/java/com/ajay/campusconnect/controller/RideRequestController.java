package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.RideRequestDto;
import com.ajay.campusconnect.dto.RideRequestResponse;
import com.ajay.campusconnect.service.RideRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    private final RideRequestService rideRequestService;

    public RideRequestController(RideRequestService rideRequestService) {
        this.rideRequestService = rideRequestService;
    }

    @PostMapping
    public RideRequestResponse requestRide(
            @RequestBody RideRequestDto dto) {
        System.out.println(">>> RideRequestController reached");

        return rideRequestService.requestRide(dto);
    }

}