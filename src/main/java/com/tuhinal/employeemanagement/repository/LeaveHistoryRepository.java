package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, String> {
}
