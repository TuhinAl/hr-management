package com.tuhinal.employeemanagement.enums;


public enum RoleTypeEnum {
  SUPER_ADMIN("SUPER_ADMIN", "Super Admin"),
  ADMIN("ADMIN", "Admin"),
  EMPLOYEE("EMPLOYEE", "Employee");

  private final String key;
  private final String value;

  RoleTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
