package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeAccountDto;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.EmployeeAccountService;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class EmployeeAccountController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final EmployeeAccountService employeeAccountService;
    
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody EmployeeAccountDto employeeAccountDto) {
        return responseFactory.saveResponse(employeeAccountService.register(employeeAccountDto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        return employeeAccountService.login(userRequest);
    }
    
 /*   @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return responseFactory.saveResponse(employeeAccountService.logout());
    }*/
    
}