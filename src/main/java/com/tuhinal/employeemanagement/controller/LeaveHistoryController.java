package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.dto.EmployeeInfoSearchDto;
import com.tuhinal.employeemanagement.dto.LeaveHistoryDto;
import com.tuhinal.employeemanagement.service.EmployeeInfoService;
import com.tuhinal.employeemanagement.service.LeaveHistoryService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/leave-history")
@Slf4j
@AllArgsConstructor
public class LeaveHistoryController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final LeaveHistoryService leaveHistoryService;
    
    @PostMapping()
    public ResponseEntity<ApiResponse<LeaveHistoryDto>> save(@RequestBody LeaveHistoryDto leaveHistoryDto) {
        return responseFactory.saveResponse(leaveHistoryService.save(leaveHistoryDto));
    }

    @PostMapping("/adjust-leave")
    public ResponseEntity<ApiResponse<LeaveHistoryDto>> search(@RequestBody LeaveHistoryDto leaveHistoryDto) {
        return responseFactory.getResponse(leaveHistoryService.changeStatus(leaveHistoryDto));
    }
    
}