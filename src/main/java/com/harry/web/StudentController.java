package com.harry.web;


import com.harry.domain.Student;
import com.harry.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/get-one/{id}")
    @ResponseBody
    public Student getOne(@PathVariable("id") Integer id) {
        return studentService.findOne(id);
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Student> getALL() {
        return studentService.findAll();
    }


}
