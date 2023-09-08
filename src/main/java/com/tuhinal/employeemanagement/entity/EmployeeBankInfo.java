package com.tuhinal.employeemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_bank_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeBankInfo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "branch_name")
    private String branchName;
    
    @Column(name = "account_number")
    private String accountNumber;
    
    @Column(name = "current_balance")
    private Double currentBalance;
    
    @Column(name = "enabled", nullable = false)
    protected Boolean enabled = true;
    
    public EmployeeBankInfo(String id) {
        this.id = id;
    }
}
