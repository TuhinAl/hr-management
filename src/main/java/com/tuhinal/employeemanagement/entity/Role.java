package com.tuhinal.employeemanagement.entity;

import com.tuhinal.employeemanagement.enums.RoleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "role_type_enum_key")
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum roleTypeEnumKey;

    @Column(name = "role_type_enum_value")
    private String roleTypeEnumValue;

    public Role(String id) {
        this.id = id;
    }

    public Role(RoleTypeEnum roleTypeEnumKey) {
        this.roleTypeEnumKey = roleTypeEnumKey;
    }
}
