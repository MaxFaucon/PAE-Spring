package com.example.demo.web;

import com.example.demo.business.PAE;
import com.example.demo.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class CourseRestController {

    @Autowired
    private PAE pae;

    @GetMapping("/courses")
    public Iterable<Course> courses() {
        return pae.getCourses();
    }
}
