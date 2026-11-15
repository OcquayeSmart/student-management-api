package com.smartocquaye.studentmanagementapi.dtos;

import com.smartocquaye.studentmanagementapi.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password field should not be empty")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;

    //enums use NotNull not NotBlank
    @NotNull(message = "Role should not be empty")
    private Role role;
}
