package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.LoginRequest;
import com.ajay.campusconnect.dto.LoginResponse;
import com.ajay.campusconnect.security.JwtService;
import com.ajay.campusconnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;


    public AuthController(AuthService authService,
                          JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
    @GetMapping("/profile")
    public String profile() {
        return "Welcome to your profile";
    }
}