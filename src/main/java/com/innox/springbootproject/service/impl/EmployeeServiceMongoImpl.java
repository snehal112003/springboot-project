package com.innox.springbootproject.service.impl;

import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;
import com.innox.springbootproject.repository.mongo.EmployeeInfoMongoRepository;
import com.innox.springbootproject.service.EmployeeServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceMongoImpl implements EmployeeServiceMongo {

    private final EmployeeInfoMongoRepository employeeRepository;

    @Autowired
    public EmployeeServiceMongoImpl(EmployeeInfoMongoRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeInfoMongo save(EmployeeInfoMongo employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeInfoMongo> saveAll(List<EmployeeInfoMongo> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<EmployeeInfoMongo> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeInfoMongo> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeInfoMongo update(String id, EmployeeInfoMongo employee) {
        Optional<EmployeeInfoMongo> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            EmployeeInfoMongo existingEmployee = existing.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setSalary(employee.getSalary());
            return employeeRepository.save(existingEmployee);
        } else {
            return null; // Or throw custom exception
        }
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }
}
