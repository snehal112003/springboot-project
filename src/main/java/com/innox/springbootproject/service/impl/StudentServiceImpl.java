package com.innox.springbootproject.service.impl;

import com.innox.springbootproject.model.Student;
import com.innox.springbootproject.repository.StudentRepository;
import com.innox.springbootproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

