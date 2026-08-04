package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.RideRequestDto;
import com.ajay.campusconnect.dto.RideRequestResponse;
import com.ajay.campusconnect.service.RideRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    private final RideRequestService rideRequestService;

    public RideRequestController(RideRequestService rideRequestService) {
        this.rideRequestService = rideRequestService;
    }

    @PostMapping
    public RideRequestResponse requestRide(@RequestBody RideRequestDto dto) {
        return rideRequestService.requestRide(dto);
    }

    @GetMapping("/my")
    public List<RideRequestResponse> getMyRequests() {
        return rideRequestService.getMyRequests();
    }

    @GetMapping("/received")
    public List<RideRequestResponse> getReceivedRequests() {
        return rideRequestService.getReceivedRequests();
    }

    @PutMapping("/{id}/accept")
    public RideRequestResponse acceptRequest(@PathVariable Long id) {
        return rideRequestService.acceptRequest(id);
    }

    @PutMapping("/{id}/reject")
    public RideRequestResponse rejectRequest(@PathVariable Long id) {
        return rideRequestService.rejectRequest(id);
    }
}