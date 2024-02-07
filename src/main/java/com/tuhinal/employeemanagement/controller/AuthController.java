package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.AuthService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
@AllArgsConstructor
public class AuthController {

    private final ApiResponseEntityFactory responseFactory;
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EmployeeInfoDto>> registerUser(@RequestBody EmployeeInfoDto employeeInfoDto) {
        return responseFactory.saveResponse(authService.register(employeeInfoDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        return authService.login(userRequest);
    }

    @PostMapping("/notices")
    public String profile() {
        return "This is your notice";
    }

 /*   @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return responseFactory.saveResponse(employeeAccountService.logout());
    }*/

}