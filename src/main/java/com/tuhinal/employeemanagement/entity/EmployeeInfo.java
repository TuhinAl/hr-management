package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeInfo {
    
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
    
    @Column(name = "payment_type_enum_key")
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentTypeEnumKey;
    
    @Column(name = "payment_type_enum_value")
    private String paymentTypeEnumValue;
    
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_account_id")
    private EmployeeAccount employeeAccount;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_account_transaction_id")
    private EmployeeAccountTransaction employeeAccountTransaction;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_attendance_id")
    private EmployeeAttendance employeeAttendance;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_bank_info_id")
    private EmployeeBankInfo employeeBankInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "employee_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public EmployeeInfo(String id) {
        this.id = id;
    }
}
