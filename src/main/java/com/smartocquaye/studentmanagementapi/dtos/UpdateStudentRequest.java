package com.smartocquaye.studentmanagementapi.dtos;

import lombok.Data;
import lombok.Getter;

@Getter
public class UpdateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
}
