package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeAccountDto;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.AuthService;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/auth")
@AllArgsConstructor
public class AuthController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final AuthService authService;
    
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody EmployeeAccountDto employeeAccountDto) {
        return responseFactory.saveResponse(authService.register(employeeAccountDto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        return authService.login(userRequest);
    }
    
 /*   @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return responseFactory.saveResponse(employeeAccountService.logout());
    }*/
    
}