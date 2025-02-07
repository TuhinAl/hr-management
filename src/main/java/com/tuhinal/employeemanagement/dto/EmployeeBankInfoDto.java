package com.tuhinal.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeBankInfoDto {
    
    private String id;
    private String bankName;
    private String branchName;
    private String accountNumber;
    private Double currentBalance;
    private Boolean enabled;
    
    public EmployeeBankInfoDto(String id) {
        this.id = id;
    }
}
