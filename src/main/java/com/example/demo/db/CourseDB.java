package com.example.demo.db;

import java.util.List;

import com.example.demo.model.Course;

import org.springframework.data.repository.CrudRepository;

public interface CourseDB extends CrudRepository<Course, String>{

    List<Course> findByCreditsGreaterThanEqual(Integer credits);

    List<Course> findByIdContaining(String text);
    
}
