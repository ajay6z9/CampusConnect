package com.ajay.campusconnect.service;

import com.ajay.campusconnect.dto.LoginRequest;
import com.ajay.campusconnect.dto.LoginResponse;
import com.ajay.campusconnect.entity.User;
import com.ajay.campusconnect.exception.InvalidCredentialsException;
import com.ajay.campusconnect.repository.UserRepository;
import com.ajay.campusconnect.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(jwtService.generateToken(user.getEmail()))
                .message("Login successful")
                .build();
    }

}