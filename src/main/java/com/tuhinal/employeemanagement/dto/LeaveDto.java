package com.tuhinal.employeemanagement.dto;

import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.enums.LeaveTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LeaveDto {
    private String id;

    private Double totalSickLeaves;
    private Double remainingSickLeaves;
    private Double totalCasualLeaves;
    private Double remainingCasualLeaves;
    private Double totalEarnedLeaves;
    private Double remainingEarnedLeaves;

    private Double numberOfDaysLeave;
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum takenLeaveType;

    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum earnedLeaveName;

    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum casualLeaveName;

    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum sickLeaveName;

    protected Boolean isCurrentYear = true;
    private EmployeeInfo employeeInfo;

    private String employeeInfoId;

    public LeaveDto(String id) {
        this.id = id;
    }
}
