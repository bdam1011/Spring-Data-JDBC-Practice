package com.harry.web;


import com.harry.domain.Student;
import com.harry.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * StudentController
 *
 * @author harrywang
 */
@Controller
@RequestMapping("/")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("")
    public String index() {
        return "/index";
    }

    @GetMapping("add")
    private String add(Model model) {
        model.addAttribute("student", new Student());
        return "/input";
    }

    @PostMapping("add")
    private String insert(Student student) {
        studentService.add(student);
        return "/index";
    }

    @GetMapping("update/{id}")
    private String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("student", studentService.findOne(id));
        return "/input";
    }

    @PostMapping("update")
    private String update(Student student) {
        studentService.update(student);
        return "/index";
    }


}
