package com.ajay.campusconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;
}