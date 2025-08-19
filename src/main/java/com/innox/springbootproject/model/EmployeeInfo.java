package com.innox.springbootproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)   // ensures JPA validates it too
    private String name;
    private String department;
    private Double salary;

    // Constructors
    public EmployeeInfo() {
    }

    public EmployeeInfo(String name, String department, Double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public EmployeeInfo(long l, String john, String developer, int i) {
    }

    public EmployeeInfo(Long o, String mark, String manager, int i) {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
