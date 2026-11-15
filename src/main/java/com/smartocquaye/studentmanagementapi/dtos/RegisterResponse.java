package com.smartocquaye.studentmanagementapi.dtos;

import com.smartocquaye.studentmanagementapi.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String email;
    private Role role;
}
