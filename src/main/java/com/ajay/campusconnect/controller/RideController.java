package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.RideRequest;
import com.ajay.campusconnect.dto.RideResponse;
import com.ajay.campusconnect.service.RideService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    @GetMapping
    public List<RideResponse> getAllRides() {
        return rideService.getAllRides();
    }
    @GetMapping("/{id}")
    public RideResponse getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }
    @PutMapping("/{id}")
    public RideResponse updateRide(
            @PathVariable Long id,
            @Valid @RequestBody RideRequest request) {

        return rideService.updateRide(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteRide(@PathVariable Long id) {

        rideService.deleteRide(id);

        return "Ride deleted successfully";
    }
    @GetMapping("/search")
    public List<RideResponse> searchRides(

            @RequestParam String source,

            @RequestParam String destination) {

        return rideService.searchRides(source, destination);
    }
    @GetMapping("/my")
    public List<RideResponse> getMyRides() {
        return rideService.getMyRides();
    }
}