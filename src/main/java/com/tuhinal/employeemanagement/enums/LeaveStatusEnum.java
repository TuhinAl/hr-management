package com.tuhinal.employeemanagement.enums;


public enum LeaveStatusEnum {
  PENDING("PENDING", "Pending"),
  MANAGER_APPROVED("MANAGER_APPROVED", "Manager Approved"),
  SECTION_HEAD_APPROVED("SECTION_HEAD_APPROVED", "Section Head Approved"), // department head
  HR_APPROVED("HR_APPROVED", "Hr Approved");

  private final String key;
  private final String value;

  LeaveStatusEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
