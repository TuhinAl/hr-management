package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.EmployeeInfoDto;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.entity.Role;
import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.repository.RoleRepository;
import com.tuhinal.employeemanagement.security.config.UserDetailsImpl;
import com.tuhinal.employeemanagement.security.config.UserDetailsServiceImpl;
import com.tuhinal.employeemanagement.security.filter.JwtUtil;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final IdGeneratorService idGeneratorService;
    private final EmployeeInfoRepository employeeInfoRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    Logger log = LoggerFactory.getLogger(AuthService.class);

    @Transactional
    public EmployeeInfoDto register(EmployeeInfoDto employeeInfoDto) {

        log.info("Registering user: {}", employeeInfoDto.getUsername());

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
    public UserResponse login(UserRequest userRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        UserDetailsImpl user =  (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtUtil.generateJwtToken(userRequest.getUsername(), authentication);
        UserResponse userResponse = new UserResponse(user.getId(), token, "Login Successful");
        return userResponse;
    }
    
/*    @Transactional
    public ResponseCookie logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return cookie;
    }*/


    public Set<Role> getRoles(Set<Role> roleSet) {
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
