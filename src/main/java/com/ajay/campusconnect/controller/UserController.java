package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.UserRequest;
import com.ajay.campusconnect.dto.UserResponse;
import com.ajay.campusconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }
    @GetMapping("/profile")
    public String profile() {
        return "Welcome User";
    }
}