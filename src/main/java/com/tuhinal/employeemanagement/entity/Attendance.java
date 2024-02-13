package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.AttendanceEntryTypeEnum;
import com.tuhinal.employeemanagement.enums.AttendanceEnum;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_attendance")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Attendance extends Auditable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    //todo need to map
    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "employee_nc_id")
    private String employeeNcId;

    @Column(name = "weekday")
    private String weekDay;

    @Column(name = "attendance_entry_type_key") // Check-In/Check-Out
    @Enumerated(EnumType.STRING)
    private AttendanceEnum attendanceEntryTypeKey;

    @Column(name = "attendance_entry_type_value")
    private String attendanceEntryTypeValue;

    @Column(name = "attendance_Leave_type_key") // Check-In/Check-Out
    @Enumerated(EnumType.STRING)
    private AttendanceEnum attendanceLeaveTypeKey;

    @Column(name = "attendance_Leave_type_value")
    private String attendanceLeaveTypeValue;

    @Column(name = "entry_type_key") // OnTime/Late
    @Enumerated(EnumType.STRING)
    private AttendanceEntryTypeEnum entryTypeKey;

    @Column(name = "entry_type_value")
    private String entryTypeValue;

    @Column(name = "late_reason")
    private String lateReason;

    @Column(name = "leave_type_key") // OnTime/Late
    @Enumerated(EnumType.STRING)
    private AttendanceEntryTypeEnum leaveTypeKey;

    @Column(name = "leave_type_value")
    private String leaveTypeValue;

    @Column(name = "early_leave_minutes")
    private Long earlyLeaveMinutes;

    //todo will add overtime business/ will add overtime button
    /*@Column(name = "early_leave_minutes")
    private Double earlyLeaveMinutes;*/

    @Column(name = "in_time_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime inTimeAt;

    @Column(name = "out_time_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime outTimeAt;

    @Column(name = "date_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAt;

    @Column(name = "is_already_checked_in")
    private Boolean isAlreadyCheckedIn = false;

    @Column(name = "is_already_checked_out")
    private Boolean isAlreadyCheckedOut = false;


    public Attendance(String id) {
        this.id = id;
    }
}
