package com.harry.service;

import com.harry.domain.Course;

public interface CourseService extends ServiceInterface<Course> {

    Course findTeachersWithCourse(Integer id);
}
