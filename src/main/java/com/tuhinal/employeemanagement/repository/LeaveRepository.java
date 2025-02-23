package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, String> {
}
