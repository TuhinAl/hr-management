package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.AttendanceEntryTypeEnum;
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


@Entity
@Table(name = "employee_leave_history")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LeaveHistory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "leave_start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leaveStartDate;

    @Column(name = "leave_end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leaveEndDate;

    @Column(name = "number_of_days", nullable = false)
    private Double numberOfDays;

    @Column(name = "time_off_key") // OnTime/Late
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum timeOfKey;

    @Column(name = "time_off_value")
    private String timeOffValue;

    @Column(name = "is_half_day_leave")
    private Boolean isHalfDayLeave;

    @Column(name = "half_day_leave_key") // OnTime/Late
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum halfDayLeaveKey;

    @Column(name = "half_day_leave_value")
    private String halfDayLeaveValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id", nullable = false)
    private EmployeeInfo employeeInfo;

    @Column(name = "employee_info_id", insertable = false, updatable = false)
    private String employeeInfoId;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "employee_account_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();


    public LeaveHistory(String id) {
        this.id = id;
    }
}
