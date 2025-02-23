package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, String> {
}
