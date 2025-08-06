package com.innox.springbootproject.repository;

import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<EmployeeInfoMongo, String> {
}
