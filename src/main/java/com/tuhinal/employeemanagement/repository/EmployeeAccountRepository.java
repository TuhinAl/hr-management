package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccountRepository
        extends JpaRepository<EmployeeAccount, String>, QuerydslPredicateExecutor<EmployeeAccount> {
    
    Optional<EmployeeAccount> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}
