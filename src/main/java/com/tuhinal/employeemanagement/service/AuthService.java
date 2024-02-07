package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.entity.Role;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.repository.RoleRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final PasswordEncoder passwordEncoder;
    private final IdGeneratorService idGeneratorService;
    private final EmployeeInfoRepository employeeInfoRepository;
    private final RoleRepository roleRepository;

    
    
    @Transactional
    public EmployeeInfoDto register(EmployeeInfoDto employeeInfoDto) {
        if (employeeInfoRepository.existsByUsername(employeeInfoDto.getUsername())) {
            throw new EntityExistsException("Error: Username is already taken!");
        }
        
        if (employeeInfoRepository.existsByEmail(employeeInfoDto.getEmail())) {
            throw new EntityExistsException("Error: Email is already in use!");
        }

        var employeeInfo = copyProp(employeeInfoDto, EmployeeInfo.class);

        employeeInfo.setRoles(employeeInfoDto.getRole());
        employeeInfo.setEmployeeNcId(idGeneratorService.empIdGenerator());
        employeeInfo.setPassword(passwordEncoder.encode(employeeInfoDto.getPassword()));
        employeeInfoRepository.save(employeeInfo);

        return copyProp(employeeInfo, EmployeeInfoDto.class);
    }
    
    
    @Transactional
    public ResponseEntity<UserResponse> login(UserRequest userRequest) {
        
        
     /*   Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                userRequest.getPassword()));
        String token = "";
        if (authentication.isAuthenticated()) {
             token = jwtUtil.generateJwtToken(userRequest.getUsername());
        }*/
       
        
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(new UserResponse("tokenNNN", "Login Successful"));
        return responseEntity;
    }
    
/*    @Transactional
    public ResponseCookie logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return cookie;
    }*/


    public Set<Role> getRoles( Set<Role> roleSet) {
        Set<Role> roles = new HashSet<>();
        return roleSet = roleSet.stream().map(r -> {
            Role role = new Role();
            if (r.getRoleTypeEnumKey().equals(RoleTypeEnum.EMPLOYEE)) {
                role.setRoleTypeEnumKey(RoleTypeEnum.EMPLOYEE);
                role.setRoleTypeEnumValue(RoleTypeEnum.EMPLOYEE.getValue());
            }
            if (r.getRoleTypeEnumKey().equals(RoleTypeEnum.SUPER_ADMIN)) {
                role.setRoleTypeEnumKey(RoleTypeEnum.SUPER_ADMIN);
                role.setRoleTypeEnumValue(RoleTypeEnum.SUPER_ADMIN.getValue());
            }
            if (r.getRoleTypeEnumKey().equals(RoleTypeEnum.ADMIN)) {
                role.setRoleTypeEnumKey(RoleTypeEnum.ADMIN);
                role.setRoleTypeEnumValue(RoleTypeEnum.ADMIN.getValue());
            }
            roles.add(role);
            return roles;
        }).flatMap(Set::stream).collect(Collectors.toSet());
    }
    
    
    
    
    
    
    
    
    
    
    
    

/*
    public Page<EmployeeInfoDto> search(EmployeeInfoSearchDto employeeInfoSearchDto) {

        final QOtRequisition qOtRequisition = QOtRequisition.otRequisition;
        final JPAQuery<OtRequisition> otRequisitionJPAQuery = new JPAQuery<>(entityManager);

        Predicate predicate = searchPredicate(otRequisitionSearchDto);
        Pageable pageable = PageRequest.of(otRequisitionSearchDto.getPage(), otRequisitionSearchDto.getSize());

        var query = otRequisitionJPAQuery
                .from(qOtRequisition)
                .where(predicate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qOtRequisition.createdDate.desc());

        return new PageImpl<>(copyList(query.fetch(), EmployeeInfoDto.class), pageable, query.fetchCount());
    }
*/

}
