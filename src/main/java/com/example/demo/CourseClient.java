package com.example.demo;

import java.util.Arrays;

import com.example.demo.model.Course;

import org.springframework.web.client.RestTemplate;

public class CourseClient {
    
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Course[] courses = restTemplate.getForObject(
            "http://localhost:8080/api/courses", Course[].class);

        Arrays.stream(courses).forEach(System.out::println);
    }
    
}
