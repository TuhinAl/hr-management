package com.tuhinal.employeemanagement.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String token;
    private String message;
    private Boolean isEnabled;

    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public UserResponse(String username, String token, String message) {
        this.username = username;
        this.token = token;
        this.message = message;
    }

    public UserResponse(String username, String email, String id, String token, Boolean isEnabled, String message) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.token = token;
        this.message = message;
        this.isEnabled = isEnabled;
    }
}
