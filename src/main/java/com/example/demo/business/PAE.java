package com.example.demo.business;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Course;
import com.example.demo.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PAE {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    public Iterable<Course> getCourses() {
        return courseService.getCourses();
    }

    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

    public Iterable<Student> getStudents() {
        return studentService.getStudents();
    }

    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }

    public Course getCourse(String id) {
        Optional<Course> course = courseService.getCourse(id);

        return course.isPresent() ? course.get() : null;
    }

    public Student getStudent(Long id) {
        Optional<Student> student = studentService.getStudent(id);

        return student.isPresent() ? student.get() : null;
    }

    public void addStudentToCourse(Long matricule, String id) {
        Student student = this.getStudent(matricule);
        Course course = this.getCourse(id);

        if (student != null && course != null) {
            course.getStudents().add(student);
            student.getCourses().add(course);
            courseService.addCourse(course);
            studentService.addStudent(student);
        }
    }

    public List<Student> getStudentsWithName(String string) {
        return studentService.getStudentsWithName(string);
    }

}
