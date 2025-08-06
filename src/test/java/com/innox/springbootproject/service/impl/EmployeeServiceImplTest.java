package com.innox.springbootproject.service.impl;

import com.innox.springbootproject.model.EmployeeInfo;
import com.innox.springbootproject.repository.EmployeeRepository;
import com.innox.springbootproject.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        EmployeeInfo employee = new EmployeeInfo("John", "IT", 50000.0);
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeInfo result = employeeService.save(employee);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testSaveAll() {
        List<EmployeeInfo> employees = Arrays.asList(
                new EmployeeInfo("A", "HR", 40000.0),
                new EmployeeInfo("B", "IT", 60000.0)
        );
        when(employeeRepository.saveAll(employees)).thenReturn(employees);

        List<EmployeeInfo> result = employeeService.saveAll(employees);

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).saveAll(employees);
    }

    @Test
    void testGetAll() {
        List<EmployeeInfo> employees = List.of(new EmployeeInfo("C", "Sales", 30000.0));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeInfo> result = employeeService.getAll();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetById_WhenExists() {
        EmployeeInfo employee = new EmployeeInfo("D", "Finance", 70000.0);
        employee.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<EmployeeInfo> result = employeeService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("D", result.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_WhenNotExists() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<EmployeeInfo> result = employeeService.getById(99L);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdate() {
        EmployeeInfo existing = new EmployeeInfo("Old", "HR", 40000.0);
        existing.setId(1L);

        EmployeeInfo updated = new EmployeeInfo("New", "Tech", 60000.0);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(EmployeeInfo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeInfo result = employeeService.update(1L, updated);

        assertEquals("New", result.getName());
        assertEquals("Tech", result.getDepartment());
        assertEquals(60000.0, result.getSalary());
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(existing);
    }

    @Test
    void testDelete() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.delete(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}


