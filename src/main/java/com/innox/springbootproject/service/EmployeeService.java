package com.innox.springbootproject.service;

import com.innox.springbootproject.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> saveAll(List<Employee> employees);
    List<Employee> getAll();
    Optional<Employee> getById(Long id);
    Employee update(Long id, Employee employee);
    void delete(Long id);
}
