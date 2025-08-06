package com.innox.springbootproject.controller;

import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeInfo save(@RequestBody EmployeeInfo employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<EmployeeInfo> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeInfo> getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeeInfo update(@PathVariable Long id, @RequestBody EmployeeInfo employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
