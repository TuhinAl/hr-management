package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, String>, QuerydslPredicateExecutor<Attendance> {
    Attendance findAttendanceByEmployeeId(String employeeId);

}
