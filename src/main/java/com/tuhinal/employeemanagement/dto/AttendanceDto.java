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

    @Enumerated(EnumType.STRING)// Check-In/Check-Out
    private AttendanceEnum attendanceTypeKey;
    private String attendanceTypeValue;

    @Enumerated(EnumType.STRING)// OnTime/Late
    private AttendanceEntryTypeEnum entryTypeKey;
    private String entry_type_Value;

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
    public AttendanceDto(String id) {
        this.id = id;
    }
}
