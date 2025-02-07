package com.tuhinal.employeemanagement.service;


import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.dto.AttendanceDto;
import com.tuhinal.employeemanagement.entity.Attendance;
import com.tuhinal.employeemanagement.entity.QAttendance;
import com.tuhinal.employeemanagement.enums.AttendanceEntryTypeEnum;
import com.tuhinal.employeemanagement.enums.AttendanceEnum;
import com.tuhinal.employeemanagement.repository.AttendanceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EntityManager entityManager;

    @Transactional
    public AttendanceDto checkInOut(AttendanceDto attendanceDto) throws Exception {

        Attendance newAttendance = new Attendance();
        Attendance attendanceByEmployeeId = fetchAttendanceOfEmployee(attendanceDto);

        if (Objects.nonNull(attendanceByEmployeeId)) {
            if (attendanceByEmployeeId.getIsAlreadyCheckedIn() &&
                    attendanceDto.getCheckInOutTypeKey().equals(AttendanceEnum.CHECK_IN)) {
                attendanceDto.setIsAlreadyCheckedIn(Boolean.TRUE);
                return attendanceDto;
            }

            if (attendanceDto.getCheckInOutTypeKey().equals(AttendanceEnum.CHECK_IN)) {
                LocalDateTime inTime = LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
                LocalTime desiredTime = LocalTime.of(8, 35, 59);
                if (inTime.toLocalTime().isAfter(desiredTime)) {
                    attendanceByEmployeeId.setEntryTypeKey(AttendanceEntryTypeEnum.LATE);
                    attendanceByEmployeeId.setEntryTypeValue(AttendanceEntryTypeEnum.LATE.getValue());
                    // TODO set late time

                } else {
                    attendanceByEmployeeId.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                    attendanceByEmployeeId.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
                }
                attendanceByEmployeeId.setEarlyLeaveMinutes(0L);
                attendanceByEmployeeId.setInTimeAt(inTime);
                attendanceByEmployeeId.setDateAt(LocalDate.now());
                attendanceByEmployeeId.setIsAlreadyCheckedIn(Boolean.TRUE);
                attendanceByEmployeeId.setAttendanceEntryTypeKey(AttendanceEnum.CHECK_IN);
                attendanceByEmployeeId.setAttendanceEntryTypeValue(AttendanceEnum.CHECK_IN.getValue());

            }

            if (attendanceDto.getCheckInOutTypeKey().equals(AttendanceEnum.CHECK_OUT)) {
                LocalDateTime outTime = LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
                LocalTime desiredTime = LocalTime.of(6, 0, 0);
                LocalDateTime targetDateTime = outTime.with(desiredTime);
                Duration duration = Duration.between(outTime, targetDateTime);
                long minutes = duration.toMinutes();
                if (outTime.toLocalTime().isBefore(desiredTime)) {
                    attendanceByEmployeeId.setLeaveTypeKey(AttendanceEntryTypeEnum.EARLY_LEAVE);
                    attendanceByEmployeeId.setLeaveTypeValue(AttendanceEntryTypeEnum.EARLY_LEAVE.getValue());
                    attendanceByEmployeeId.setEarlyLeaveMinutes(minutes);
                } else {
                    attendanceByEmployeeId.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                    attendanceByEmployeeId.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
                    attendanceByEmployeeId.setEarlyLeaveMinutes(0L);
                }
                attendanceByEmployeeId.setOutTimeAt(outTime);
                attendanceByEmployeeId.setIsAlreadyCheckedOut(Boolean.TRUE);
                attendanceByEmployeeId.setAttendanceLeaveTypeKey(AttendanceEnum.CHECK_OUT);
                attendanceByEmployeeId.setAttendanceLeaveTypeValue(AttendanceEnum.CHECK_OUT.getValue());
            }
            newAttendance = attendanceRepository.save(attendanceByEmployeeId);
        } else {
            Attendance attendance = copyProp(attendanceDto, Attendance.class);
            if (Objects.nonNull(attendance)) {
                if (attendanceDto.getCheckInOutTypeKey().equals(AttendanceEnum.CHECK_IN)) {
                    LocalDateTime inTime = LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
                    LocalTime desiredTime = LocalTime.of(9, 0, 59);
                    if (inTime.toLocalTime().isAfter(desiredTime)) {
                        attendance.setEntryTypeKey(AttendanceEntryTypeEnum.LATE);
                        attendance.setEntryTypeValue(AttendanceEntryTypeEnum.LATE.getValue());
                    } else {
                        attendance.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                        attendance.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
                    }
                    attendance.setEarlyLeaveMinutes(0L);
                    attendance.setInTimeAt(inTime);
                    attendance.setDateAt(LocalDate.now());
                    attendance.setIsAlreadyCheckedIn(Boolean.TRUE);
                    attendance.setAttendanceEntryTypeKey(AttendanceEnum.CHECK_IN);
                    attendance.setAttendanceEntryTypeValue(AttendanceEnum.CHECK_IN.getValue());

                }

                if (attendanceDto.getCheckInOutTypeKey().equals(AttendanceEnum.CHECK_OUT)) {
                    LocalDateTime outTime = LocalDateTime.now(ZoneId.of("Asia/Dhaka"));
                    LocalTime desiredTime = LocalTime.of(6, 0, 0);
                    LocalDateTime targetDateTime = outTime.with(desiredTime);
                    Duration duration = Duration.between(outTime, targetDateTime);
                    long minutes = duration.toMinutes();
                    if (outTime.toLocalTime().isBefore(desiredTime)) {
                        attendance.setEntryTypeKey(AttendanceEntryTypeEnum.EARLY_LEAVE);
                        attendance.setEntryTypeValue(AttendanceEntryTypeEnum.EARLY_LEAVE.getValue());
                        attendance.setEarlyLeaveMinutes(minutes);
                    } else {
                        attendance.setEntryTypeKey(AttendanceEntryTypeEnum.ON_TIME);
                        attendance.setEntryTypeValue(AttendanceEntryTypeEnum.ON_TIME.getValue());
                        attendance.setEarlyLeaveMinutes(0L);
                    }
                    attendance.setOutTimeAt(outTime);
                    attendance.setIsAlreadyCheckedOut(Boolean.TRUE);
                    attendance.setAttendanceLeaveTypeKey(AttendanceEnum.CHECK_OUT);
                    attendance.setAttendanceLeaveTypeValue(AttendanceEnum.CHECK_OUT.getValue());
                }
            }
            if (Objects.nonNull(attendance)) {
                newAttendance = attendanceRepository.save(attendance);
            }
        }
        AttendanceDto attendanceDtoDuplicate = copyProp(newAttendance, AttendanceDto.class);
        attendanceDtoDuplicate.setCheckInOutTypeKey(attendanceDto.getCheckInOutTypeKey());
        return attendanceDtoDuplicate;
    }


    public Attendance fetchAttendanceOfEmployee(AttendanceDto attendanceDto) {

        final QAttendance qAttendance = QAttendance.attendance;
        final JPAQuery<Attendance> query = new JPAQuery<>(entityManager);

        return query
                .from(qAttendance)
                .where(qAttendance.employeeId.eq(attendanceDto.getEmployeeId())
                        .and(qAttendance.dateAt.eq(LocalDate.now())))
                .fetchFirst();
    }

    public Page<AttendanceDto> getEmployeeWiseAttendance(AttendanceDto attendanceDto) {

        Pageable pageable = PageRequest.of(attendanceDto.getPage(), attendanceDto.getSize());
        final QAttendance qAttendance = QAttendance.attendance;
        final JPAQuery<Attendance> query = new JPAQuery<>(entityManager);

        List<Attendance> attendanceList = query.from(qAttendance)
                .where(qAttendance.createdDate.between(attendanceDto.getFromDate()
                        , attendanceDto.getToDate()).and(qAttendance.employeeId.eq(attendanceDto.getEmployeeId())))
                .fetch();
        List<AttendanceDto> attendanceDtoList = attendanceList.stream().map(attendance -> {
            return copyProp(attendance, AttendanceDto.class);
        }).toList();
        return new PageImpl<>(attendanceDtoList, pageable, attendanceDto.getSize());
    }

}
