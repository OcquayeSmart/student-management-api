package com.smartocquaye.studentmanagementapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Email is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
}
