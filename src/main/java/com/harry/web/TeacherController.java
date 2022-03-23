package com.harry.web;


import com.harry.domain.Teacher;
import com.harry.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * TeacherController
 *
 * @author shenghui
 */
@Controller
@RequestMapping("/")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @RequestMapping("")
    public String index() {
        return "/index";
    }

    @GetMapping("add")
    private String add(Model model){
        model.addAttribute("teacher",new Teacher());
        return "/input";
    }

    @PostMapping("add")
    private String insert(Teacher teacher){
        teacherService.add(teacher);
        return "/index";
    }

    @GetMapping("update")
    private String edit(@RequestParam("id") Integer id,Model model){
        model.addAttribute("teacher",teacherService.findOne(id));
        return "input";
    }

    @PostMapping("update")
    private String update(Teacher teacher){
        teacherService.update(teacher);
        return "/index";
    }



}
