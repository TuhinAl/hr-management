package com.tuhinal.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeAccountDto {
    
    private String id;
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private Set<String> role;
    
//    private EmployeeInfoDto employeeInfoDto;
    public EmployeeAccountDto(String id) {
        this.id = id;
    }
}
