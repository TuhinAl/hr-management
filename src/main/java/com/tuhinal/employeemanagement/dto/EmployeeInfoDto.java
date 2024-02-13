package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.entity.Role;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeInfoDto {
    
    private String id;
    
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Set<Role> role;
    private String employeeId;
    private String employeeNcId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String address;
    
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentTypeEnumKey;
    private String paymentTypeEnumValue;
    
    @Enumerated(EnumType.STRING)
    private DesignationTypeEnum designationTypeEnumKey;
    private String designationTypeEnumValue;

    private EmployeeAccountTransactionDto employeeAccountTransactionDto;
    private EmployeeAttendanceDto employeeAttendanceDto;
    private EmployeeBankInfoDto employeeBankInfoDto;
    
    private Boolean enabled;
    
    public EmployeeInfoDto(String id) {
        this.id = id;
    }
}
