package com.ajay.campusconnect.service;

import com.ajay.campusconnect.dto.RideRequest;
import com.ajay.campusconnect.dto.RideResponse;
import com.ajay.campusconnect.entity.Ride;
import com.ajay.campusconnect.entity.User;
import com.ajay.campusconnect.repository.RideRepository;
import com.ajay.campusconnect.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public RideService(RideRepository rideRepository,
                       UserRepository userRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    public RideResponse createRide(RideRequest request) {

        // Temporary
        User driver = userRepository.findByEmail("vijay@gmail.com")
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Ride ride = Ride.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .availableSeats(request.getAvailableSeats())
                .price(request.getPrice())
                .description(request.getDescription())
                .driver(driver)
                .build();

        Ride savedRide = rideRepository.save(ride);

        return RideResponse.builder()
                .id(savedRide.getId())
                .source(savedRide.getSource())
                .destination(savedRide.getDestination())
                .departureTime(savedRide.getDepartureTime())
                .availableSeats(savedRide.getAvailableSeats())
                .price(savedRide.getPrice())
                .description(savedRide.getDescription())
                .driverName(savedRide.getDriver().getName())
                .build();
    }
}