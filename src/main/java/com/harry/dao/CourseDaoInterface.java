package com.harry.dao;

import com.harry.domain.Course;


public interface CourseDaoInterface extends DaoInterface<Course> {
    Course findTeachersWithCourse(Integer id);
}
