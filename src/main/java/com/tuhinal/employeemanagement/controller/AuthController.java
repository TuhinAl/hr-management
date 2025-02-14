package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.security.filter.JwtUtil;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.AuthService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final ApiResponseEntityFactory responseFactory;
    private final AuthService authService;
    private final JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EmployeeInfoDto>> registerUser(@RequestBody EmployeeInfoDto employeeInfoDto) {
        return responseFactory.saveResponse(authService.register(employeeInfoDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody UserRequest userRequest) {

        return responseFactory.saveResponse(authService.login(userRequest));
    }

    @PostMapping("/api/logout")
    public ResponseEntity<ApiResponse<UserResponse>> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtUtil.invalidateToken(token);
        }
        SecurityContextHolder.clearContext();
        return responseFactory.updateResponse("");
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