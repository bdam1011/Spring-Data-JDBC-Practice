package com.harry.web;


import com.harry.domain.Teacher;
import com.harry.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * TeacherController
 *
 * @author harrywang
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @RequestMapping("")
    public String index() {
        return "/index";
    }

    @GetMapping("add")
    private String add(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "/input-teacher";
    }

    @PostMapping("add")
    private String insert(Teacher teacher) {
        teacherService.add(teacher);
        return "/index";
    }

    @GetMapping("update/{id}")
    private String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("teacher", teacherService.findOne(id));
        return "/input-teacher";
    }

    @PostMapping("update")
    private String update(Teacher teacher) {
        teacherService.update(teacher);
        return "/index";
    }

    @GetMapping("/get-one/{id}")
    @ResponseBody
    public Teacher getOne(@PathVariable("id") Integer id) {
        return teacherService.findOne(id);
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Teacher> getALL() {
        return teacherService.findAll();
    }


}
