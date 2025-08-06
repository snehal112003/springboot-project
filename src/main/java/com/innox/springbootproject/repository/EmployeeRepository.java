package com.innox.springbootproject.repository;

import com.innox.springbootproject.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {
}
