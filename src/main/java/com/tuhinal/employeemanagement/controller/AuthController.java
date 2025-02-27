package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.security.config.UserDetailsImpl;
import com.tuhinal.employeemanagement.security.filter.JwtTokenProvider;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.AuthService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final ApiResponseEntityFactory responseFactory;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;
    private final UserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EmployeeInfoDto>> registerUser(@RequestBody EmployeeInfoDto employeeInfoDto) {
        return responseFactory.saveResponse(authService.register(employeeInfoDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        /* SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(authentication);
        UserResponse userResponse = new UserResponse(userDetails.getUsername(), userDetails.getEmail(),
                userDetails.getId(), token, userDetails.isEnabled(), "Success");
        return responseFactory.saveResponse(userResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<UserResponse>> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
//            jwtTokenProvider.invalidateToken(token);
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