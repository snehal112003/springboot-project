package com.innox.springbootproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private double salary;

    // ✅ No-arg constructor required by JPA
    public Employee() {
    }

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
