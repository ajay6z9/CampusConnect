package com.ajay.campusconnect.service;

import com.ajay.campusconnect.dto.RideRequestDto;
import com.ajay.campusconnect.dto.RideRequestResponse;
import com.ajay.campusconnect.entity.RequestStatus;
import com.ajay.campusconnect.entity.Ride;
import com.ajay.campusconnect.entity.RideRequest;
import com.ajay.campusconnect.entity.User;
import com.ajay.campusconnect.repository.RideRepository;
import com.ajay.campusconnect.repository.RideRequestRepository;
import com.ajay.campusconnect.repository.UserRepository;
import com.ajay.campusconnect.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class RideRequestService {

    private final RideRepository rideRepository;
    private final RideRequestRepository rideRequestRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    public RideRequestService(
            RideRepository rideRepository,
            RideRequestRepository rideRequestRepository,
            UserRepository userRepository,
            SecurityUtils securityUtils) {

        this.rideRepository = rideRepository;
        this.rideRequestRepository = rideRequestRepository;
        this.userRepository = userRepository;
        this.securityUtils = securityUtils;
    }

    public RideRequestResponse requestRide(RideRequestDto dto) {
        System.out.println(">>> RideRequestService reached");
        Ride ride = rideRepository.findById(dto.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        String email = securityUtils.getCurrentUserEmail();

        User passenger = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RideRequest rideRequest = RideRequest.builder()
                .ride(ride)
                .passenger(passenger)
                .status(RequestStatus.PENDING)
                .build();

        RideRequest savedRequest = rideRequestRepository.save(rideRequest);

        return RideRequestResponse.builder()
                .requestId(savedRequest.getId())
                .passengerName(passenger.getName())
                .driverName(ride.getDriver().getName())
                .source(ride.getSource())
                .destination(ride.getDestination())
                .status(savedRequest.getStatus().name())
                .build();
    }
}