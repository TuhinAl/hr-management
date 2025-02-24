package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.LeaveDto;
import com.tuhinal.employeemanagement.entity.Leave;
import com.tuhinal.employeemanagement.enums.LeaveTypeEnum;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.repository.LeaveRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Transactional
    public LeaveDto adjustLeave(LeaveDto leaveDto) {

        Leave leave = leaveRepository.findLeaveByEmployeeInfoIdAndCurrentYear(
                leaveDto.getEmployeeInfoId(), LocalDateTime.now());

        if (leaveDto.getTakenLeaveType().equals(LeaveTypeEnum.EARNED)) {
            double remainingLeave = leave.getRemainingEarnedLeaves() - leaveDto.getNumberOfDaysLeave();
            leave.setRemainingCasualLeaves(remainingLeave);
        }
        if (leaveDto.getTakenLeaveType().equals(LeaveTypeEnum.CASUAL)) {
            double remainingLeave = leave.getRemainingCasualLeaves() - leaveDto.getNumberOfDaysLeave();
            leave.setRemainingCasualLeaves(remainingLeave);
        }
        if (leaveDto.getTakenLeaveType().equals(LeaveTypeEnum.SICK)) {
            double remainingLeave = leave.getRemainingSickLeaves() - leaveDto.getNumberOfDaysLeave();
            leave.setRemainingSickLeaves(remainingLeave);
        }
        Leave persistedLeave = leaveRepository.save(leave);
        LeaveDto newLeaveDto = copyProp(persistedLeave, LeaveDto.class);
        return copyProp(newLeaveDto, LeaveDto.class);
    }

    public static void validateLeaveDto(LeaveDto leaveDto) throws IllegalArgumentException {

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
    }
}
