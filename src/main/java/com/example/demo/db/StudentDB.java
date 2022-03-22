package com.example.demo.db;

import java.util.List;

import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentDB extends CrudRepository<Student, Long> {
    
    List<Student> findByNameContaining(String string);

    @Query("SELECT s.name FROM Student s")
    List<String> findAllStudentNames();

    @Query("SELECT s.name, s.matricule FROM Student s")
    List<Object[]> findAllStudentNamesAndMatricules();

    @Query("SELECT s.name, SUM(c.credits) FROM Student s JOIN s.courses c GROUP BY s.name")
    List<Object[]> findAllStudentNamesAndCreditsSum();

    @Query("SELECT s FROM Student s JOIN s.courses c GROUP BY s HAVING SUM(c.credits) > :credits ")
    List<Student> findStudentByCreditsAbove(long credits);

}
