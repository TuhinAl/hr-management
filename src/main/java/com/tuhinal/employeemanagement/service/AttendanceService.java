package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.AttendanceDto;
import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.entity.Attendance;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.enums.AttendanceEntryTypeEnum;
import com.tuhinal.employeemanagement.enums.AttendanceEnum;
import com.tuhinal.employeemanagement.repository.AttendanceRepository;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class AttendanceService {
    
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public AttendanceDto checkInOut(AttendanceDto attendanceDto ) {

        Attendance attendance = copyProp(attendanceDto, Attendance.class);
        if (attendanceDto.getAttendanceTypeKey().equals(AttendanceEnum.CHECK_IN)) {
            LocalDateTime inTime =  LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
            LocalTime desiredTime = LocalTime.of(9, 0, 59);
            if (inTime.toLocalTime().isAfter(desiredTime)) {
                attendance.setEntryTypeKey(AttendanceEntryTypeEnum.LATE);
                attendance.setEntryTypeValue(AttendanceEntryTypeEnum.LATE.getValue());
            }else {
                attendance.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                attendance.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
            }
            attendance.setEarlyLeaveMinutes(0L);
            attendance.setInTimeAt(inTime);
        }

        if (attendanceDto.getAttendanceTypeKey().equals(AttendanceEnum.CHECK_OUT)) {
            LocalDateTime outTime =  LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
            LocalTime desiredTime = LocalTime.of(6, 0, 0);
            LocalDateTime targetDateTime = outTime.with(desiredTime);
            Duration duration = Duration.between(outTime, targetDateTime);
            long minutes = duration.toMinutes();
            if (outTime.toLocalTime().isBefore(desiredTime)) {
                attendance.setEntryTypeKey(AttendanceEntryTypeEnum.EARLY_LEAVE);
                attendance.setEntryTypeValue(AttendanceEntryTypeEnum.EARLY_LEAVE.getValue());
                attendance.setEarlyLeaveMinutes(minutes);
            }else {
                attendance.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                attendance.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
                attendance.setEarlyLeaveMinutes(0L);
            }
            attendance.setOutTimeAt(outTime);
        }

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return copyProp(savedAttendance, AttendanceDto.class);
    }


}
