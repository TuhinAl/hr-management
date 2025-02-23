package com.tuhinal.employeemanagement.service;


import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.dto.EmployeeInfoSearchDto;
import com.tuhinal.employeemanagement.dto.LeaveDto;
import com.tuhinal.employeemanagement.dto.SalaryBreakdownDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.entity.Leave;
import com.tuhinal.employeemanagement.entity.QEmployeeInfo;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.repository.LeaveRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyList;
import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class LeaveService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    private final LeaveRepository leaveRepository;
    private final EntityManager entityManager;

    @Transactional
    public LeaveDto save(LeaveDto leaveDto) {
        var employeeInfo = employeeInfoRepository.findById(leaveDto.getEmployeeInfoId())
                .orElseThrow();
        validateLeaveDto(leaveDto);
        Leave leave = copyProp(leaveDto, Leave.class);
        if (!Objects.isNull(employeeInfo)) {
            leave.setEmployeeInfo(employeeInfo);
            leave.setEmployeeInfoId(employeeInfo.getId());
        }

        Leave persistedLeave = leaveRepository.save(leave);
        LeaveDto newLeaveDto = copyProp(persistedLeave, LeaveDto.class);
        return copyProp(newLeaveDto, LeaveDto.class);
    }

    public static void validateLeaveDto(LeaveDto leaveDto) throws IllegalArgumentException {
        if (Objects.isNull(leaveDto.getId()) || leaveDto.getId().isEmpty()) {
            throw new IllegalArgumentException("ID is required.");
        }
        if (Objects.isNull(leaveDto.getTotalSickLeaves())) {
            throw new IllegalArgumentException("Total sick leaves are required.");
        }
        if (Objects.isNull(leaveDto.getRemainingSickLeaves())) {
            throw new IllegalArgumentException("Remaining sick leaves are required.");
        }
        if (Objects.isNull(leaveDto.getTotalCasualLeaves())) {
            throw new IllegalArgumentException("Total casual leaves are required.");
        }
        if (Objects.isNull(leaveDto.getRemainingCasualLeaves())) {
            throw new IllegalArgumentException("Remaining casual leaves are required.");
        }
        if (Objects.isNull(leaveDto.getTotalEarnedLeaves())) {
            throw new IllegalArgumentException("Total earned leaves are required.");
        }
        if (Objects.isNull(leaveDto.getRemainingEarnedLeaves())) {
            throw new IllegalArgumentException("Remaining earned leaves are required.");
        }
        if (Objects.isNull(leaveDto.getEmployeeInfoId()) || leaveDto.getEmployeeInfoId().isEmpty()) {
            throw new IllegalArgumentException("Employee info ID is required.");
        }
        if (Objects.isNull(leaveDto.getCurrentYear())) {
            throw new IllegalArgumentException("Current year is required.");
        }
    }
}
