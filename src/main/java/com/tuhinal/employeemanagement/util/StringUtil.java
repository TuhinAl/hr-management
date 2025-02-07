package com.tuhinal.employeemanagement.util;

public class StringUtil {

  /**
   * @param stringVal example  "R012203000001"
   * @param substringVal example  6
   * @param incrementBy example  1
   * @return String example : "000002"    "000003"
   */
  public static String joinerStringLastPartIncrement(String stringVal,int substringVal,int incrementBy){
    int val = StringUtil.zeroPaddedStringToInt(stringVal.substring(stringVal.length() - substringVal));
    return StringUtil.intToZeroAddedString(val + incrementBy, substringVal);
  }


  /**
   * @param value example 123
   * @param size   example 9
   * @return String example : "000000123"
   */
  public static String intToZeroAddedString(Integer value, Integer size){
    if(value<0){
      return String.format("%0"+ size +"d", 0);
    }
    return String.format("%0"+ size +"d", value);
  }


  /**
   * @param value example  "0003456"
   * @return String example : 3456
   */
  public static Integer zeroPaddedStringToInt(String value){
    return Integer.parseInt(value);
  }
}
