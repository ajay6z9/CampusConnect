package com.ajay.campusconnect.service;

import com.ajay.campusconnect.dto.RideRequest;
import com.ajay.campusconnect.dto.RideResponse;
import com.ajay.campusconnect.entity.Ride;
import com.ajay.campusconnect.entity.User;
import com.ajay.campusconnect.repository.RideRepository;
import com.ajay.campusconnect.repository.UserRepository;
import com.ajay.campusconnect.security.SecurityUtils;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;
    public RideService(RideRepository rideRepository,
                       UserRepository userRepository,
                       SecurityUtils securityUtils) {

        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.securityUtils = securityUtils;
    }

    public RideResponse createRide(RideRequest request) {

        // Temporary
        String email = securityUtils.getCurrentUserEmail();

        User driver = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
    public List<RideResponse> getAllRides() {

        return rideRepository.findAll()
                .stream()
                .map(ride -> RideResponse.builder()
                        .id(ride.getId())
                        .source(ride.getSource())
                        .destination(ride.getDestination())
                        .departureTime(ride.getDepartureTime())
                        .availableSeats(ride.getAvailableSeats())
                        .price(ride.getPrice())
                        .description(ride.getDescription())
                        .driverName(ride.getDriver().getName())
                        .build())
                .toList();
    }
    public RideResponse getRideById(Long id) {

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));

        return RideResponse.builder()
                .id(ride.getId())
                .source(ride.getSource())
                .destination(ride.getDestination())
                .departureTime(ride.getDepartureTime())
                .availableSeats(ride.getAvailableSeats())
                .price(ride.getPrice())
                .description(ride.getDescription())
                .driverName(ride.getDriver().getName())
                .build();
    }
    public RideResponse updateRide(Long id, RideRequest request) {

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));

        ride.setSource(request.getSource());
        ride.setDestination(request.getDestination());
        ride.setDepartureTime(request.getDepartureTime());
        ride.setAvailableSeats(request.getAvailableSeats());
        ride.setPrice(request.getPrice());
        ride.setDescription(request.getDescription());

        Ride updatedRide = rideRepository.save(ride);

        return RideResponse.builder()
                .id(updatedRide.getId())
                .source(updatedRide.getSource())
                .destination(updatedRide.getDestination())
                .departureTime(updatedRide.getDepartureTime())
                .availableSeats(updatedRide.getAvailableSeats())
                .price(updatedRide.getPrice())
                .description(updatedRide.getDescription())
                .driverName(updatedRide.getDriver().getName())
                .build();
    }
    public void deleteRide(Long id) {

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));

        rideRepository.delete(ride);
    }
    public List<RideResponse> searchRides(String source, String destination) {

        return rideRepository
                .findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination)
                .stream()
                .map(ride -> RideResponse.builder()
                        .id(ride.getId())
                        .source(ride.getSource())
                        .destination(ride.getDestination())
                        .departureTime(ride.getDepartureTime())
                        .availableSeats(ride.getAvailableSeats())
                        .price(ride.getPrice())
                        .description(ride.getDescription())
                        .driverName(ride.getDriver().getName())
                        .build())
                .toList();
    }
}