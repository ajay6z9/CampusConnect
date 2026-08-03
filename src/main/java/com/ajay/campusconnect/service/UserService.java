package com.ajay.campusconnect.service;
import com.ajay.campusconnect.dto.UserRequest;
import com.ajay.campusconnect.dto.UserResponse;
import com.ajay.campusconnect.entity.User;
import com.ajay.campusconnect.exception.EmailAlreadyExistsException;
import com.ajay.campusconnect.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(UserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .build();
    }
}