package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeInfoSearchDto extends SearchDto{
    
    private String id;
    
    private String name;
    
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
    
    public EmployeeInfoSearchDto(String id) {
        this.id = id;
    }
}
