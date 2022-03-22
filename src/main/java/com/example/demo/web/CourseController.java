package com.example.demo.web;

import javax.validation.Valid;

import com.example.demo.business.PAE;
import com.example.demo.model.Course;
import com.example.demo.model.StudentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "courses")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private PAE pae;

    @ModelAttribute(name = "courses")
    public Iterable<Course> getCourses(Model model) {
        return pae.getCourses();
    }

    @GetMapping()
    public String course(Model model) {
        model.addAttribute("course", new Course());

        return "courses";
    }

    @PostMapping("/add")
    public String addCourse(@Valid Course course, Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "courses";
        }
        pae.addCourse(new Course(course.getId(), course.getTitle(), course.getCredits()));
        log.info("Cours ajouté :");
        log.info("Sigle : " + course.getId() + " - Titre : " + course.getTitle() + " - ECTS : " + course.getCredits());

        return "redirect:/courses";
    }

    @GetMapping("/{courseId}/details")
    public String detail(@PathVariable(value="courseId") String courseId, Model model) {
        log.info("============================ Début détails : " + pae.getCourse(courseId).getStudents());

        Course course = pae.getCourse(courseId);

        model.addAttribute("student", new StudentDto());
        model.addAttribute("students", course.getStudents());
        model.addAttribute("courseDetails", course);

        log.info("============================ Fin détails");
        return "courseDetails";
    }

    @PostMapping("/{courseId}/details/add")
    public String addStudent(StudentDto student, @PathVariable(value="courseId") String courseId) {
        pae.addStudentToCourse(student.getMatricule(), courseId);

        return "redirect:/courses/" + courseId + "/details";
    }
}