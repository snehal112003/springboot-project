package com.innox.springbootproject.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
//
import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@ActiveProfiles("test") // ensures application-test.properties is loaded
//class EmployeeIntegrationTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @BeforeEach
//    void setUp() {
//        // Clean DB before each test
//        employeeRepository.deleteAll();
//
//        // Insert sample data into H2 (IDs auto-generated)
//        EmployeeInfo emp1 = new EmployeeInfo(null, "John", "Developer", 50000);
//        EmployeeInfo emp2 = new EmployeeInfo(null, "Jane", "Tester", 45000);
//
//       //employeeRepository.saveAllAndFlush(Arrays.asList(emp1));
//    }
//
//    @Test
//    void testGetAllEmployees() {
//        ResponseEntity<EmployeeInfo[]> response =
//                restTemplate.getForEntity("/employees", EmployeeInfo[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotEmpty();
//
//        List<EmployeeInfo> employees = Arrays.asList(response.getBody());
//        assertThat(employees).hasSize(2);
//        assertThat(employees.get(0).getName()).isEqualTo("John");
//    }
//
//    @Test
//    void testCreateEmployee() {
//        EmployeeInfo newEmp = new EmployeeInfo(null, "Mark", "Manager", 70000);
//
//        ResponseEntity<EmployeeInfo> response =
//                restTemplate.postForEntity("/employees", newEmp, EmployeeInfo.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isNotNull();
//        assertThat(response.getBody().getName()).isEqualTo("Mark");
//
//        // Verify DB contains the new employee
//        assertThat(employeeRepository.findAll()).hasSize(3);
//    }
//}
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.innoxIT.springbootApplication_project.model.EmployeeInfo;
//import com.innoxIT.springbootApplication_project.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll(); // clear test DB before each test
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeInfo emp = new EmployeeInfo(null, "John Doe", "IT", 50000);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        List<EmployeeInfo> employees = employeeRepository.findAll();
        assertThat(employees).hasSize(1);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        employeeRepository.save(new EmployeeInfo(null, "Alice", "HR", 40000));
        employeeRepository.save(new EmployeeInfo(null, "Bob", "Finance", 60000));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeInfo emp = employeeRepository.save(new EmployeeInfo(null, "Alice", "HR", 40000));

        emp.setName("Alice Updated");
        emp.setDepartment("IT");
        emp.setSalary(55000.0);

        mockMvc.perform(put("/employees/" + emp.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        EmployeeInfo emp = employeeRepository.save(new EmployeeInfo(null, "Mark", "IT", 70000));

        mockMvc.perform(delete("/employees/" + emp.getId()))
                .andExpect(status().isNoContent());

        assertThat(employeeRepository.findAll()).isEmpty();
    }
}
