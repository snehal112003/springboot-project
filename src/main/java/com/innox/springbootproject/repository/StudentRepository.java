package com.innox.springbootproject.repository;

import com.innox.springbootproject.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}

