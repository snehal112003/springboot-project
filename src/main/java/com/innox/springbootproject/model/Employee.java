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

        public Employee(String name,String position, double salary){
            this.name=name;
            this.position=position;
            this.salary=salary;
        }
        // Getters and Setters
    }


