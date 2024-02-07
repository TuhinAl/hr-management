package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.Role;
import com.tuhinal.employeemanagement.enums.EmployeeRole;
import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
        extends JpaRepository<Role, String> {

    Optional<Role> findByRoleTypeEnumKey(RoleTypeEnum roleTypeEnumKey);
}
