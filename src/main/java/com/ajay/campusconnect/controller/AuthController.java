package com.ajay.campusconnect.controller;

import com.ajay.campusconnect.dto.LoginRequest;
import com.ajay.campusconnect.dto.LoginResponse;
import com.ajay.campusconnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

}