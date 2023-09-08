package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAccountTransactionRepository
        extends JpaRepository<EmployeeAccountTransaction, String>, QuerydslPredicateExecutor<EmployeeAccountTransaction> {
    
    
}
