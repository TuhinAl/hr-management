package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.entity.EmployeeInfo;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static com.tuhinal.employeemanagement.security.config.UserDetailsImpl.build;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    
    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeInfo employeeInfo = employeeInfoRepository.findEmployeeInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return build(employeeInfo);
    }
}
