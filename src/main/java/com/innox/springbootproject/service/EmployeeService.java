package com.innox.springbootproject.service;

import com.innox.springbootproject.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> getAll();
    Employee getById(Long id);
    void delete(Long id);
}