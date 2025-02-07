package com.tuhinal.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class Contact {
    
    @GetMapping("/contact")
    public String profile() {
        return "This is your contact number";
    }
}
