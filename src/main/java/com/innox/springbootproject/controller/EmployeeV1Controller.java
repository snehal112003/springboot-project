package com.innox.springbootproject.controller;

import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;
import com.innox.springbootproject.service.EmployeeServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeV1Controller {

    private final EmployeeServiceMongo employeeServiceMongo;

    @Autowired
    public EmployeeV1Controller(EmployeeServiceMongo employeeServiceMongo) {
        this.employeeServiceMongo = employeeServiceMongo;
    }

    @PostMapping
    public EmployeeInfoMongo save(@RequestBody EmployeeInfoMongo employee) {
        return employeeServiceMongo.save(employee);
    }

    @GetMapping
    public List<EmployeeInfoMongo> getAll() {
        return employeeServiceMongo.getAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeInfoMongo> getById(@PathVariable String id) {
        return employeeServiceMongo.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeeInfoMongo update(@PathVariable String id, @RequestBody EmployeeInfoMongo employee) {
        return employeeServiceMongo.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        employeeServiceMongo.delete(id);
    }
}
