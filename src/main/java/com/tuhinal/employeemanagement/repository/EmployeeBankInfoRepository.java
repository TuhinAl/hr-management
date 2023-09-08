package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeBankInfoRepository
        extends JpaRepository<EmployeeBankInfo, String>, QuerydslPredicateExecutor<EmployeeBankInfo> {
    
    
}
