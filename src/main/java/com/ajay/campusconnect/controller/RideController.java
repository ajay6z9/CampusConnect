package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.RideRequest;
import com.ajay.campusconnect.dto.RideResponse;
import com.ajay.campusconnect.service.RideService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public RideResponse createRide(
            @Valid @RequestBody RideRequest request) {

        return rideService.createRide(request);
    }
}