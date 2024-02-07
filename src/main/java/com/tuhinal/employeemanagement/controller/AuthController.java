package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
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
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody UserRequest userRequest) {

        return responseFactory.saveResponse(authService.login(userRequest));
    }

    @PostMapping("/notices")
    public EmployeeInfoDto profile() {
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        employeeInfoDto.setFirstName("Alauddin");
        employeeInfoDto.setLastName("Tuhin");
        employeeInfoDto.setUsername("pagla");
        employeeInfoDto.setEmployeeNcId("121");
        return employeeInfoDto;
    }

 /*   @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return responseFactory.saveResponse(employeeAccountService.logout());
    }*/

}