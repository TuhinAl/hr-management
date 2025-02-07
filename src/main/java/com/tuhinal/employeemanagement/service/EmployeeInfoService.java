package com.tuhinal.employeemanagement.service;


import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.dto.EmployeeInfoSearchDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.entity.QEmployeeInfo;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyList;
import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeInfoService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    private final IdGeneratorService idGeneratorService;
    private final EntityManager entityManager;

    @Transactional
    public EmployeeInfoDto save(EmployeeInfoDto employeeInfoDto) {
        var employeeInfo = copyProp(employeeInfoDto, EmployeeInfo.class);
//        employeeInfo.setEnabled(Boolean.TRUE);
        employeeInfo.setDob(LocalDate.now());
//        employeeInfo.setEmployeeNcId(idGeneratorService.employeeIdGenerator());
        EmployeeInfo employeeInfoFromDb = employeeInfoRepository.save(employeeInfo);
        return copyProp(employeeInfoFromDb, EmployeeInfoDto.class);
    }

    public Page<EmployeeInfoDto> search(EmployeeInfoSearchDto employeeInfoSearchDto) {

        final QEmployeeInfo qEmployeeInfo = QEmployeeInfo.employeeInfo;
        final JPAQuery<EmployeeInfo> otRequisitionJPAQuery = new JPAQuery<>(entityManager);

//        Predicate predicate = searchPredicate(otRequisitionSearchDto);
        Pageable pageable = PageRequest.of(employeeInfoSearchDto.getPage(), employeeInfoSearchDto.getSize());

        var query = otRequisitionJPAQuery
                .from(qEmployeeInfo)
                .where(qEmployeeInfo.id.eq(employeeInfoSearchDto.getId()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
//                .orderBy(qEmployeeInfo.at.desc());

        return new PageImpl<>(copyList(query.fetch(), EmployeeInfoDto.class), pageable, query.fetchCount());
    }

}
