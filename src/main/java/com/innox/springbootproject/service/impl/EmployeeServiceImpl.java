package com.innox.springbootproject.service.impl;

import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.repository.EmployeeRepository;
import com.innox.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeInfo save(EmployeeInfo employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeInfo> saveAll(List<EmployeeInfo> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<EmployeeInfo> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeInfo> getById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeInfo update(Long id, EmployeeInfo employee) {
        Optional<EmployeeInfo> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            EmployeeInfo updated = existing.get();
            updated.setName(employee.getName());
            updated.setDepartment(employee.getDepartment());
            updated.setSalary(employee.getSalary());
            return employeeRepository.save(updated);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
