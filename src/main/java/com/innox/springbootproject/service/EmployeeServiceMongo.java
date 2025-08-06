package com.innox.springbootproject.service;

import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceMongo {

    EmployeeInfoMongo save(EmployeeInfoMongo employee);

    List<EmployeeInfoMongo> saveAll(List<EmployeeInfoMongo> employees); // ✅ Corrected type

    List<EmployeeInfoMongo> getAll();

    Optional<EmployeeInfoMongo> getById(String id);

    EmployeeInfoMongo update(String id, EmployeeInfoMongo employee);

    void delete(String id);
}
