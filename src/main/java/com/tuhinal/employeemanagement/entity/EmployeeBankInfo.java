package com.tuhinal.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id")
    private EmployeeInfo employeeInfo;

    @Column(name = "enabled", nullable = false)
    protected Boolean enabled = true;
    
    public EmployeeBankInfo(String id) {
        this.id = id;
    }
}
