package com.example.employeeservice.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<String> employees = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

    // GET /api/employees
    @GetMapping
    public List<String> getEmployees() {
        return employees;
    }

    // POST /api/employees accepting form parameter "name"
    @PostMapping
    public String addEmployee(@RequestParam String name) {
        employees.add(name);
        return "Employee '" + name + "' added successfully.";
    }
}
