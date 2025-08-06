package com.innox.springbootproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innox.springbootproject.model.mongo.EmployeeInfoMongo;
import com.innox.springbootproject.service.EmployeeServiceMongo;
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

@WebMvcTest(EmployeeV1Controller.class)
public class EmployeeV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceMongo employeeServiceMongo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        EmployeeInfoMongo emp1 = new EmployeeInfoMongo("1", "Alice", "HR", 40000.0);
        EmployeeInfoMongo emp2 = new EmployeeInfoMongo("2", "Bob", "IT", 55000.0);

        Mockito.when(employeeServiceMongo.getAll()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(get("/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetById() throws Exception {
        EmployeeInfoMongo emp = new EmployeeInfoMongo("1", "Alice", "HR", 40000.0);

        Mockito.when(employeeServiceMongo.getById("1")).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testSave() throws Exception {
        EmployeeInfoMongo emp = new EmployeeInfoMongo("1", "Alice", "HR", 40000.0);

        Mockito.when(employeeServiceMongo.save(any(EmployeeInfoMongo.class))).thenReturn(emp);

        mockMvc.perform(post("/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testUpdate() throws Exception {
        EmployeeInfoMongo updated = new EmployeeInfoMongo("1", "Alice Updated", "Finance", 60000.0);

        Mockito.when(employeeServiceMongo.update(eq("1"), any(EmployeeInfoMongo.class))).thenReturn(updated);

        mockMvc.perform(put("/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department").value("Finance"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/v1/employees/1"))
                .andExpect(status().isOk());
    }
}
