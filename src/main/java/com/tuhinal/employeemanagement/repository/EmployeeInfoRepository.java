package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeInfoRepository
        extends JpaRepository<EmployeeInfo, String>, QuerydslPredicateExecutor<EmployeeInfo> {

    Optional<EmployeeInfo> findEmployeeInfoByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
