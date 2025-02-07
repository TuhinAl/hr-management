package com.tuhinal.employeemanagement.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserResponse {

    private String username;
    private String token;
    private String message;

    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public UserResponse(String username, String token, String message) {
        this.username = username;
        this.token = token;
        this.message = message;
    }
}
