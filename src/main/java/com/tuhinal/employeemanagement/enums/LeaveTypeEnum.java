package com.tuhinal.employeemanagement.enums;


public enum LeaveTypeEnum {

  PENDING("PENDING", "Pending"),
  RECEIVED("RECEIVED", "Received"),
  DISBURSED("DISBURSED", "Disbursed");

  private final String key;
  private final String value;

  LeaveTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
