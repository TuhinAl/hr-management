package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Integer totalSickLeaves;

    @Column(name = "remaining_sick_leaves", nullable = false)
    private Integer remainingSickLeaves;

    @Column(name = "total_casual_leaves", nullable = false)
    private Integer totalCasualLeaves;

    @Column(name = "remaining_casual_leaves", nullable = false)
    private Integer remainingCasualLeaves;

    @Column(name = "total_earned_leaves", nullable = false)
    private Integer totalEarnedLeaves;

    @Column(name = "remaining_earned_leaves", nullable = false)
    private Integer remainingEarnedLeaves;

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

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "employee_account_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();


    public Leave(String id) {
        this.id = id;
    }
}
