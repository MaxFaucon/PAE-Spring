package com.example.demo.business;

import java.util.Optional;

import com.example.demo.db.CourseDB;
import com.example.demo.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseDB courseDB;

    public Iterable<Course> getCourses() {
        // System.out.println(courseDB.findByCreditsGreaterThanEqual(5));
        // System.out.println(courseDB.findByIdContaining("DEV"));
        return courseDB.findAll();
    }

    public Course addCourse(Course course) {
        return courseDB.save(course);
    }

    public Optional<Course> getCourse(String id) {
        return courseDB.findById(id);
    }
    
}
