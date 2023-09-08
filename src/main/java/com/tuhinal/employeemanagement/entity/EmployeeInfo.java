package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
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
    
    @Column(name = "name")
    private String name;
    
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
    
   public EmployeeInfo(String id) {
        this.id = id;
    }
}
