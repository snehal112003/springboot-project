package com.innox.springbootproject.service;

import com.innox.springbootproject.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeInfo save(EmployeeInfo employee);
    List<EmployeeInfo> saveAll(List<EmployeeInfo> employees);
    List<EmployeeInfo> getAll();
    Optional<EmployeeInfo> getById(Long id);
    EmployeeInfo update(Long id, EmployeeInfo employee);
    void delete(Long id);
}
