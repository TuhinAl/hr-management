package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.LeaveDto;
import com.tuhinal.employeemanagement.service.LeaveService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/leave")
@Slf4j
@AllArgsConstructor
public class LeaveController {

    private final ApiResponseEntityFactory responseFactory;
    private final LeaveService leaveService;

    @PostMapping()
    public ResponseEntity<ApiResponse<LeaveDto>> save(@RequestBody LeaveDto leaveDto) {
        return responseFactory.saveResponse(leaveService.save(leaveDto));
    }

}