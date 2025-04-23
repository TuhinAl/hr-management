package com.tuhinal.employeemanagement.service;

import com.tuhinal.employeemanagement.dto.ApprovedDto;
import com.tuhinal.employeemanagement.dto.LeaveDto;
import com.tuhinal.employeemanagement.dto.LeaveHistoryDto;
import com.tuhinal.employeemanagement.entity.LeaveHistory;
import com.tuhinal.employeemanagement.enums.LeaveStatusEnum;
import com.tuhinal.employeemanagement.enums.LeaveTypeEnum;
import com.tuhinal.employeemanagement.repository.LeaveHistoryRepository;
import com.tuhinal.employeemanagement.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;

@Service
@RequiredArgsConstructor
public class LeaveHistoryService {

    private final LeaveHistoryRepository leaveHistoryRepository;
    private final LeaveService leaveService;

    @Transactional
    public LeaveHistoryDto save(LeaveHistoryDto leaveHistoryDto) {
        LeaveHistory leaveHistory = copyProp(leaveHistoryDto, LeaveHistory.class);
        leaveHistory.setLeaveStatusKey(LeaveStatusEnum.PENDING);
        leaveHistory.setLeaveStatusValue(LeaveStatusEnum.PENDING.getValue());
        LeaveHistory persistedLeaveHistory = leaveHistoryRepository.save(leaveHistory);
        return copyProp(persistedLeaveHistory, LeaveHistoryDto.class);
    }

    @Transactional
    public LeaveHistoryDto changeStatus(ApprovedDto approvedDto) {
        LeaveHistory leaveHistory = leaveHistoryRepository.findById(approvedDto.getId()).orElseThrow();
        if(approvedDto.getLeaveStatusKey().equals(LeaveStatusEnum.MANAGER_APPROVED)) {
            leaveHistory.setLeaveStatusKey(LeaveStatusEnum.MANAGER_APPROVED);
            leaveHistory.setLeaveStatusValue(LeaveStatusEnum.MANAGER_APPROVED.getValue());
            leaveHistory.setManagerId(approvedDto.getApprovedById());
            leaveHistory.setManagerName(approvedDto.getApprovedByName());
            leaveHistory.setManagerApproveDate(LocalDateTime.now());
        }
        if(approvedDto.getLeaveStatusKey().equals(LeaveStatusEnum.SECTION_HEAD_APPROVED)) {
            leaveHistory.setLeaveStatusKey(LeaveStatusEnum.SECTION_HEAD_APPROVED);
            leaveHistory.setLeaveStatusValue(LeaveStatusEnum.SECTION_HEAD_APPROVED.getValue());
            leaveHistory.setSectionHeadId(approvedDto.getApprovedById());
            leaveHistory.setSectionHeadName(approvedDto.getApprovedByName());
            leaveHistory.setSectionHeadApprovedDate(LocalDateTime.now());
        }
        if(approvedDto.getLeaveStatusKey().equals(LeaveStatusEnum.HR_APPROVED)) {
            leaveHistory.setLeaveStatusKey(LeaveStatusEnum.HR_APPROVED);
            leaveHistory.setLeaveStatusValue(LeaveStatusEnum.HR_APPROVED.getValue());
            leaveHistory.setFinalApprovedId(approvedDto.getApprovedById());
            leaveHistory.setFinalApprovedName(approvedDto.getApprovedByName());
            leaveHistory.setFinalApprovedDate(LocalDateTime.now());
            LeaveDto leaveDto = new LeaveDto();
            leaveDto.setEmployeeInfoId(leaveHistory.getEmployeeInfo().getId());
            leaveDto.setNumberOfDaysLeave(leaveHistory.getNumberOfDays());
            //todo: check this logic
            leaveDto.setTakenLeaveType(LeaveTypeEnum.valueOf(leaveHistory.getTimeOfKey().name()));
            leaveDto.setTakenLeaveType(LeaveTypeEnum.valueOf(leaveHistory.getTimeOfKey().name()));
            leaveService.adjustLeave(leaveDto);
        }
        LeaveHistory persistedLeaveHistory = leaveHistoryRepository.save(leaveHistory);
        return copyProp(persistedLeaveHistory, LeaveHistoryDto.class);
    }

}
