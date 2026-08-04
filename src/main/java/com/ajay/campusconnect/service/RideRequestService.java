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

import java.util.List;

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

    public List<RideRequestResponse> getMyRequests() {

        String email = securityUtils.getCurrentUserEmail();

        User passenger = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return rideRequestRepository.findByPassenger(passenger)
                .stream()
                .map(request -> RideRequestResponse.builder()
                        .requestId(request.getId())
                        .passengerName(request.getPassenger().getName())
                        .driverName(request.getRide().getDriver().getName())
                        .source(request.getRide().getSource())
                        .destination(request.getRide().getDestination())
                        .status(request.getStatus().name())
                        .build())
                .toList();
    }

    public List<RideRequestResponse> getReceivedRequests() {

        String email = securityUtils.getCurrentUserEmail();

        User driver = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return rideRequestRepository.findByRide_Driver(driver)
                .stream()
                .map(request -> RideRequestResponse.builder()
                        .requestId(request.getId())
                        .passengerName(request.getPassenger().getName())
                        .driverName(driver.getName())
                        .source(request.getRide().getSource())
                        .destination(request.getRide().getDestination())
                        .status(request.getStatus().name())
                        .build())
                .toList();
    }
    public RideRequestResponse acceptRequest(Long requestId) {

        RideRequest request = rideRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.ACCEPTED);

        RideRequest updated = rideRequestRepository.save(request);

        return RideRequestResponse.builder()
                .requestId(updated.getId())
                .passengerName(updated.getPassenger().getName())
                .driverName(updated.getRide().getDriver().getName())
                .source(updated.getRide().getSource())
                .destination(updated.getRide().getDestination())
                .status(updated.getStatus().name())
                .build();
    }
    public RideRequestResponse rejectRequest(Long requestId) {

        RideRequest request = rideRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.REJECTED);

        RideRequest updated = rideRequestRepository.save(request);

        return RideRequestResponse.builder()
                .requestId(updated.getId())
                .passengerName(updated.getPassenger().getName())
                .driverName(updated.getRide().getDriver().getName())
                .source(updated.getRide().getSource())
                .destination(updated.getRide().getDestination())
                .status(updated.getStatus().name())
                .build();
    }
}