package com.smartocquaye.studentmanagementapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String accessToken;

    private String tokenType;

    private Long expiresInSeconds;
}
