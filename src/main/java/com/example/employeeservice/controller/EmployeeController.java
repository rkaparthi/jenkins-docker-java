package com.example.employeeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping
    public List<String> getEmployees() {
        return List.of("Alice", "Bob", "Charlie");
    }
}
