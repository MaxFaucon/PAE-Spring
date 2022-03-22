package com.example.demo.business;

import java.util.List;
import java.util.Optional;

import com.example.demo.db.StudentDB;
import com.example.demo.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    @Autowired
    private StudentDB studentDB;

    public Iterable<Student> getStudents() {
        // studentDB.findAllStudentNames().stream().forEach(System.out::println);
        // studentDB.findAllStudentNamesAndMatricules().stream()
        //     .forEach(object -> Arrays.stream(object).forEach(System.out::println));
        // studentDB.findAllStudentNamesAndCreditsSum().stream()
        //     .forEach(object -> Arrays.stream(object).forEach(System.out::println));
        // studentDB.findStudentByCreditsAbove(20).stream()
        //     .forEach(System.out::println);
        return studentDB.findAll();
    }

    public Student addStudent(Student student) {
        return studentDB.save(student);
    }

    public Optional<Student> getStudent(Long id) {
        return studentDB.findById(id);
    }

    public List<Student> getStudentsWithName(String string) {
        return studentDB.findByNameContaining(string);
    }

}
