package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.enums.LeaveTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;



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
