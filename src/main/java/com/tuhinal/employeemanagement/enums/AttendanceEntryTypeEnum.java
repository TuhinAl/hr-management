package com.tuhinal.employeemanagement.enums;


public enum AttendanceEntryTypeEnum {
  ON_TIME("ON_TIME", "On Time"),
  EARLY_LEAVE("EARLY_LEAVE", "Early Leave"),
  LATE("LATE", "Late");

  private final String key;
  private final String value;

  AttendanceEntryTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
