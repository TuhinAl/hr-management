package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttendanceRepository
        extends JpaRepository<EmployeeAttendance, String>, QuerydslPredicateExecutor<EmployeeAttendance> {
    
    
}
