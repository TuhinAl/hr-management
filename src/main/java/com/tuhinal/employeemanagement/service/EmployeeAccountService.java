package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.EmployeeAccountDto;
import com.tuhinal.employeemanagement.security.filter.JwtUtil;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.entity.Role;
import com.tuhinal.employeemanagement.enums.EmployeeRole;
import com.tuhinal.employeemanagement.repository.EmployeeAccountRepository;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.repository.RoleRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeAccountService {
    
    private final EmployeeAccountRepository employeeAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdGeneratorService idGeneratorService;
    private final EmployeeInfoRepository employeeInfoRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    
    
    
    @Transactional
    public EmployeeAccountDto register(EmployeeAccountDto employeeAccountDto) {
        if (employeeAccountRepository.existsByUsername(employeeAccountDto.getUsername())) {
            throw new EntityExistsException("Error: Username is already taken!");
        }
        
        if (employeeAccountRepository.existsByEmail(employeeAccountDto.getEmail())) {
            throw new EntityExistsException("Error: Email is already in use!");
        }
        
        // Create new user's account
        EmployeeAccount employeeAccount = new EmployeeAccount(employeeAccountDto.getUsername(),
                employeeAccountDto.getEmail(),
                passwordEncoder.encode(employeeAccountDto.getPassword()));
        
        Set<String> strRoles = employeeAccountDto.getRole();
        Set<Role> roles = new HashSet<>();
        
        if (strRoles == null) {
            Role userRole = roleRepository.findByEmployeeRole(EmployeeRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByEmployeeRole(EmployeeRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByEmployeeRole(EmployeeRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        
                        break;
                    default:
                        Role userRole = roleRepository.findByEmployeeRole(EmployeeRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        
        
        var employeeInfo = copyProp(employeeAccountDto, EmployeeAccount.class);
//        employeeInfo.setDob(LocalDate.now());
//        employeeInfo.setEmployeeNcId(idGeneratorService.empIdGenerator());
             employeeAccountRepository.save(employeeInfo);
        
        /*employeeAccount.setRoles(roles);
        employeeAccount.setEnabled(Boolean.TRUE);
        employeeAccountRepository.save(employeeAccount);*/
        return copyProp(employeeAccount, EmployeeAccountDto.class);
    }
    
    
    @Transactional
    public ResponseEntity<UserResponse> login(UserRequest userRequest) {
        
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                userRequest.getPassword()));
        String token = "";
        if (authentication.isAuthenticated()) {
             token = jwtUtil.generateJwtToken(userRequest.getUsername());
        }
       
        
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(new UserResponse(token, "Login Successful"));
        return responseEntity;
    }
    
/*    @Transactional
    public ResponseCookie logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return cookie;
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    

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
