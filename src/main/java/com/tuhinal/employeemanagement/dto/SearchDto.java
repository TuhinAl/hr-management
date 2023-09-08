package com.tuhinal.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
  public Integer page;
  public Integer size = 10;
  public Map<String, String> sortKeyOrderMap;
}
