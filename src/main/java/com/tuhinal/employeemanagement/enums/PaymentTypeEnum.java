package com.tuhinal.employeemanagement.enums;


public enum PaymentTypeEnum {

  PENDING("PENDING", "Pending"),
  RECEIVED("RECEIVED", "Received"),
  DISBURSED("DISBURSED", "Disbursed");

  private final String key;
  private final String value;

  PaymentTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
