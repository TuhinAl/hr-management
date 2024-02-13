package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.AttendanceDto;
import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.service.AttendanceService;
import com.tuhinal.employeemanagement.service.EmployeeInfoService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/attendance")
@Slf4j
@AllArgsConstructor
public class AttendanceController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final AttendanceService attendanceService;

    @PostMapping()
    public ResponseEntity<ApiResponse<AttendanceDto>> save(@RequestBody AttendanceDto attendanceDto) throws Exception {
        return responseFactory.saveResponse(attendanceService.checkInOut(attendanceDto));
    }

}