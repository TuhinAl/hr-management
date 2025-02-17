package com.tuhinal.employeemanagement.enums;


public enum LeaveTypeEnum {

  CASUAL("CASUAL", "Casual"),
  SICK("SICK", "Sick"),
  EARNED("EARNED", "Earned"),
  MORNING("MORNING", "Morning"),
  AFTERNOON("AFTERNOON", "Afternoon");

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
