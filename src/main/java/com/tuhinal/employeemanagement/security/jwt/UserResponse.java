package com.tuhinal.employeemanagement.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private String username;
    private String token;
    private String message;

    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
