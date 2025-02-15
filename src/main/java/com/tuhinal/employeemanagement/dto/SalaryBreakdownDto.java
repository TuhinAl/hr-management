package com.tuhinal.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryBreakdownDto {

    private Double grossSalary;
    private Double basicSalary;
    private Double houseRent;
    private Double medicalAllowance;
    private Double providentFund;
    private Double taxDeduction;
    private Double dailyAllowance;
    private Double phoneBill;
    private Double internetBill;
    private Double otherAllowance;
}
