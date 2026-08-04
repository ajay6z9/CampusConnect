package com.ajay.campusconnect.repository;

import com.ajay.campusconnect.entity.User;

import java.util.List;
import com.ajay.campusconnect.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findBySourceIgnoreCaseAndDestinationIgnoreCase(
            String source,
            String destination);

    List<Ride> findByDriver(User driver);

}