package com.example.demo.web;

import javax.validation.Valid;

import com.example.demo.business.PAE;
import com.example.demo.model.CourseDto;
import com.example.demo.model.StringDto;
import com.example.demo.model.Student;

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

@Controller
@Slf4j
@RequestMapping(value = "students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private PAE pae;
    
    @ModelAttribute(name = "students")
    public Iterable<Student> getStudents(Model model) {
        return pae.getStudents();
    }

    @ModelAttribute(name = "student")
    public Student getStudent(Model model) {
        return new Student();
    }

    @GetMapping()
    public String students(Model model) {
        model.addAttribute("stringDto", new StringDto());

        return "students";
    }

    @PostMapping("/add")
    public String addCourse(@Valid Student student, Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "students";
        }
        log.info("Etudiant ajouté :");
        log.info("Nom : " + student.getName() + " - Genre : " + student.getGender() + " - Section : " + student.getSection());
        pae.addStudent(new Student(student.getName(), student.getGender(), student.getSection()));

        return "redirect:/students";
    }

    @GetMapping("/{studentId}/details")
    public String detail(@PathVariable(value="studentId") Long studentId, Model model) {
        Student student = pae.getStudent(studentId);
        model.addAttribute("course", new CourseDto());
        model.addAttribute("courses", student.getCourses());
        model.addAttribute("studentDetails", student);

        return "studentDetails";
    }

    @PostMapping("/{studentId}/details/add")
    public String addStudent(CourseDto course, @PathVariable(value="studentId") Long studentId) {
        pae.addStudentToCourse(studentId, course.getId());

        return "redirect:/students/" + studentId + "/details";
    }

    @PostMapping("/search")
    public String searchStudents(StringDto string, Model model) {
        model.addAttribute("students", pae.getStudentsWithName(string.getString()));

        return "students";
    }
}
