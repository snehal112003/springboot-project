package com.innox.springbootproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innox.springbootproject.controller.EmployeeController;
import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEmployees() throws Exception {
        EmployeeInfo emp1 = new EmployeeInfo("John", "IT", 50000.0);
        EmployeeInfo emp2 = new EmployeeInfo("Jane", "HR", 60000.0);

        Mockito.when(employeeService.getAll()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        EmployeeInfo emp = new EmployeeInfo("John", "IT", 50000.0);
        Mockito.when(employeeService.getById(1L)).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeInfo emp = new EmployeeInfo("John", "IT", 50000.0);
        Mockito.when(employeeService.save(any(EmployeeInfo.class))).thenReturn(emp);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeInfo updated = new EmployeeInfo("John Updated", "Finance", 70000.0);
        Mockito.when(employeeService.update(eq(1L), any(EmployeeInfo.class))).thenReturn(updated);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department").value("Finance"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }
}