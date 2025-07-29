package com.innox.springbootproject.service;

import com.innox.springbootproject.model.Mongo.EmployeeInfoMongo;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceMongo {
    EmployeeInfoMongo save(EmployeeInfoMongo employee);
    List<EmployeeInfoMongo> saveAll(List<EmployeeInfoMongo> employees);
    List<EmployeeInfoMongo> getAll();
    EmployeeInfoMongo update(String id, EmployeeInfoMongo employee);
    void delete(String id);
    Optional<EmployeeInfoMongo> getById(String id);
}