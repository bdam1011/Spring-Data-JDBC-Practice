package com.harry.service;

import com.harry.domain.Teacher;

public interface TeacherService extends ServiceInterface<Teacher> {

    Teacher findStudentsWithTeacher(Integer id);

    Teacher findCoursesWithTeacher(Integer id);
}
