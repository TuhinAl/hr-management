package com.tuhinal.employeemanagement.enums;


public enum DesignationTypeEnum {

  EXECUTIVE("EXECUTIVE", "Executive"),
  MANAGER("MANAGER", "Manager"),
  ENGINEER("ENGINEER", "Engineer"),
  HUMAN_RESOURCE("HUMAN_RESOURCE", "Human Resource");

  private final String key;
  private final String value;

  DesignationTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
