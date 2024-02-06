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

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_nc_id")
    private String employeeNcId;

    @Column(name = "weekday")
    private String weekDay;

    @Column(name = "attendance_type_key") // Check-In/Check-Out
    @Enumerated(EnumType.STRING)
    private AttendanceEnum attendanceTypeKey;

    @Column(name = "attendance_type_value")
    private String attendanceTypeValue;

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


    public Attendance(String id) {
        this.id = id;
    }
}
