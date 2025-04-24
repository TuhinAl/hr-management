package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeInfo extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "employee_nc_id")
    private String employeeNcId;

    @Column(name = "dob")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name = "address")
    private String address;

    @Column(name = "designation_type_enum_key")
    @Enumerated(EnumType.STRING)
    private DesignationTypeEnum designationTypeEnumKey;

    @Column(name = "designation_type_enum_value")
    private String designationTypeEnumValue;

    @Column(name = "role_type_enum_key")
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum roleTypeEnumKey;

    @Column(name = "role_type_enum_value")
    private String roleTypeEnumValue;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<Leave> leaveList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<LeaveHistory> leaveHistoryList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<EmployeeAccountTransaction> employeeAccountTransactionList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<EmployeeAttendance> employeeAttendanceList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_bank_info_id")
    private EmployeeBankInfo employeeBankInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "employee_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"
                    , referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public EmployeeInfo(String id) {
        this.id = id;
    }
}
