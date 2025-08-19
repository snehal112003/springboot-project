package com.innox.springbootproject.integration;

import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // ensures application-test.properties is loaded
class EmployeeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        // Clean DB before each test
        employeeRepository.deleteAll();

        // Insert sample data into H2
        EmployeeInfo emp1 = new EmployeeInfo();
        emp1.setName("John");
        emp1.setDepartment("Developer");
        emp1.setSalary(50000.0);

        EmployeeInfo emp2 = new EmployeeInfo();
        emp2.setName("Jane");
        emp2.setDepartment("Tester");
        emp2.setSalary(45000.0);

        employeeRepository.saveAll(Arrays.asList(emp1, emp2));
    }

    @Test
    void testGetAllEmployees() {
        ResponseEntity<EmployeeInfo[]> response =
                restTemplate.getForEntity("/employees", EmployeeInfo[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();

        List<EmployeeInfo> employees = Arrays.asList(response.getBody());
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0).getName()).isEqualTo("John");
    }

    @Test
    void testCreateEmployee() {
        EmployeeInfo newEmp = new EmployeeInfo();
        newEmp.setName("Mark");
        newEmp.setDepartment("Manager");
        newEmp.setSalary(70000.0);

        ResponseEntity<EmployeeInfo> response =
                restTemplate.postForEntity("/employees", newEmp, EmployeeInfo.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull(); // auto-generated
        assertThat(response.getBody().getName()).isEqualTo("Mark");

        // Verify DB contains 3 employees now
        assertThat(employeeRepository.findAll()).hasSize(3);
    }
}
