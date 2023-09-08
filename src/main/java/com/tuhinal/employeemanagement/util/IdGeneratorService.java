package com.tuhinal.employeemanagement.util;


import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.entity.QEmployeeInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class IdGeneratorService {
    
    private final QEmployeeInfo qEmployeeInfo = QEmployeeInfo.employeeInfo;
    private final EntityManager entityManager;
    
   /* public String employeeIdGenerator() {
        final JPAQuery<EmployeeInfo> jpaQuery = new JPAQuery<>(entityManager);
        var employeeInfoList = jpaQuery.from(qEmployeeInfo).fetch();
        return getFormattedId("EMP01", employeeInfoList.size());
    }
    
    
    public String getFormattedId(String prefix, Integer count) {
        var yearValue = LocalDate.now().getYear();
        var monthValue = StringUtils
                .leftPad(String.valueOf(LocalDate.now().getMonthValue()), 2, '0');
        return new StringBuilder(prefix)
                .append(yearValue)
                .append(monthValue)
                .append(StringUtils
                        .leftPad(String.valueOf(count + 1), 6, '0'))
                .toString();
    }*/
    
    
    public String empIdGenerator() {
        synchronized (this) {
            LocalDate localDate = LocalDate.now();
            String year = localDate.format(DateTimeFormatter.ofPattern("yy"));
            String month = localDate.format(DateTimeFormatter.ofPattern("MM"));
            String prefixEmployeeId = "EMPO1" + "01" + year + month;
            EmployeeInfo employeeInfo = this.empId(prefixEmployeeId);
            String newEmpId;
            if (null != employeeInfo) {
                newEmpId = prefixEmployeeId + StringUtil
                        .joinerStringLastPartIncrement(employeeInfo.getEmployeeNcId(), 6, 1);
            } else {
                newEmpId = prefixEmployeeId + StringUtil.intToZeroAddedString(1, 6);
            }
            return newEmpId;
        }
    }
    
    public EmployeeInfo empId(String employeeNcId) {
        synchronized (this) {
            final QEmployeeInfo qEmployeeInfo = QEmployeeInfo.employeeInfo;
            final JPAQuery<EmployeeInfo> query = new JPAQuery<>(entityManager);
            return query.from(qEmployeeInfo)
                    .where(qEmployeeInfo.employeeNcId.like("%" + employeeNcId + "%"))
                    .orderBy(qEmployeeInfo.employeeNcId.desc())
                    .fetchFirst();
        }
    }
}
