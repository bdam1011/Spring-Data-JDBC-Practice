package com.harry.web;


import com.harry.domain.Course;
import com.harry.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * CourseController
 *
 * @author harrywang
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @RequestMapping("")
    public String index() {
        return "/index";
    }

    @GetMapping("add")
    private String add(Model model) {
        model.addAttribute("course", new Course());
        return "/input-course";
    }

    @PostMapping("add")
    private String insert(Course course) {
        courseService.add(course);
        return "/index";
    }

    @GetMapping("update/{id}")
    private String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("course", courseService.findOne(id));
        return "/input-course";
    }

    @PostMapping("update")
    private String update(Course course) {
        courseService.update(course);
        return "/index";
    }

    @GetMapping("/get-one/{id}")
    @ResponseBody
    public Course getOne(@PathVariable("id") Integer id) {
        return courseService.findOne(id);
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Course> getALL() {
        return courseService.findAll();
    }


}
