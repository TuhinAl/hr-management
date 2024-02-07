package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.repository.EmployeeAccountRepository;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeInfo employeeInfo = employeeInfoRepository.findEmployeeInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        
        return UserDetailsImpl.build(employeeInfo);
    }
}
