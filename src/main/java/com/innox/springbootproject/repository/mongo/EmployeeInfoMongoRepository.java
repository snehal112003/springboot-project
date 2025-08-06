package com.innox.springbootproject.repository.mongo;

import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoMongoRepository extends MongoRepository<EmployeeInfoMongo, String> {
}
