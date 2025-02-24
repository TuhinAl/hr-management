package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.LeaveTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "employee_leave")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Leave {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "total_sick_leaves", nullable = false)
    private Double totalSickLeaves;

    @Column(name = "remaining_sick_leaves", nullable = false)
    private Double remainingSickLeaves;

    @Column(name = "sick_leave_name")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum sickLeaveName;

    @Column(name = "total_casual_leaves", nullable = false)
    private Double totalCasualLeaves;

    @Column(name = "remaining_casual_leaves", nullable = false)
    private Double remainingCasualLeaves;

    @Column(name = "casual_leave_name")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum casualLeaveName;

    @Column(name = "total_earned_leaves", nullable = false)
    private Double totalEarnedLeaves;

    @Column(name = "remaining_earned_leaves", nullable = false)
    private Double remainingEarnedLeaves;

    @Column(name = "earned_leave_name")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum earnedLeaveName;

    @Column(name = "current_year")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currentYear;

    @Column(name = "is_current_year", nullable = false)
    protected Boolean isCurrentYear = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id", nullable = false)
    private EmployeeInfo employeeInfo;

    @Column(name = "employee_info_id", insertable = false, updatable = false)
    private String employeeInfoId;

    public Leave(String id) {
        this.id = id;
    }
}
