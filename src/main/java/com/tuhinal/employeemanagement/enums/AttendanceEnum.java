package com.tuhinal.employeemanagement.enums;


public enum AttendanceEnum {

  CHECK_IN("CHECK_IN", "Check In"),
  CHECK_OUT("CHECK_OUT", "Check Out");

  private final String key;
  private final String value;

  AttendanceEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
