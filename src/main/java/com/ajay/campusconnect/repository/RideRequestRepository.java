package com.ajay.campusconnect.repository;

import com.ajay.campusconnect.entity.Ride;
import com.ajay.campusconnect.entity.RideRequest;
import com.ajay.campusconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

    List<RideRequest> findByPassenger(User passenger);

    List<RideRequest> findByRide_Driver(User driver);

    Optional<RideRequest> findByRideAndPassenger(Ride ride, User passenger);
}