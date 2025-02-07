package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.AttendanceEntryTypeEnum;
import com.tuhinal.employeemanagement.enums.AttendanceEnum;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class AttendanceDto {

    private String id;
    private String employeeId;
    private String employeeNcId;
    private String weekDay;

    @Enumerated(EnumType.STRING)// Check-In
    private AttendanceEnum checkInOutTypeKey;

    @Enumerated(EnumType.STRING)// Check-In
    private AttendanceEnum attendanceEntryTypeKey;
    private String attendanceEntryTypeValue;

    @Enumerated(EnumType.STRING)// Check-Out
    private AttendanceEnum attendanceLeaveTypeKey;
    private String attendanceLeaveTypeValue;

    @Enumerated(EnumType.STRING)// OnTime/Late
    private AttendanceEntryTypeEnum entryTypeKey;
    private String entryTypeValue;

    @Enumerated(EnumType.STRING)// OnTime/Early leave
    private AttendanceEntryTypeEnum leaveTypeKey;
    private String leaveTypeValue;

    private Long earlyLeaveMinutes;

    private String lateReason;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime inTimeAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime outTimeAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;

    private Boolean isAlreadyCheckedIn;
    private Boolean isAlreadyCheckedOut;

    private Integer page;
    private Integer size;

    public AttendanceDto(String id) {
        this.id = id;
    }
}
