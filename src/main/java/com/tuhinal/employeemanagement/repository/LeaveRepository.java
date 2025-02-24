package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {

    Leave findLeaveByEmployeeInfoIdAndCurrentYear(String employeeId, LocalDateTime currentYear);
}
