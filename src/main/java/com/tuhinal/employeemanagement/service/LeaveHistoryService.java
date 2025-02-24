package com.tuhinal.employeemanagement.service;

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
    public LeaveHistoryDto changeStatus(LeaveHistoryDto leaveHistoryDto) {
        LeaveHistory leaveHistory = leaveHistoryRepository.findById(leaveHistoryDto.getId()).orElseThrow();
        if(leaveHistoryDto.getLeaveStatusKey().equals(LeaveStatusEnum.MANAGER_APPROVED)) {
            leaveHistory.setLeaveStatusKey(LeaveStatusEnum.MANAGER_APPROVED);
            leaveHistory.setLeaveStatusValue(LeaveStatusEnum.MANAGER_APPROVED.getValue());
        }
        if(leaveHistoryDto.getLeaveStatusKey().equals(LeaveStatusEnum.HR_APPROVED)) {
            leaveHistory.setLeaveStatusKey(LeaveStatusEnum.HR_APPROVED);
            leaveHistory.setLeaveStatusValue(LeaveStatusEnum.HR_APPROVED.getValue());
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
