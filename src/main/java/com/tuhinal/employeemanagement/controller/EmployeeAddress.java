package com.tuhinal.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class EmployeeAddress {
    
    @GetMapping("/address")
    public String profile() {
        return "This is your address";
    }
}
