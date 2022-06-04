package com.harry.service;

import com.harry.dao.CourseDaoInterface;
import com.harry.domain.Course;

public interface CourseService extends ServiceInterface<Course, CourseDaoInterface> {

    Course findTeachersWithCourse(Integer id);
}
