package com.harry.service;

import com.harry.dao.TeacherDaoInterface;
import com.harry.domain.Teacher;

public interface TeacherService extends ServiceInterface<Teacher, TeacherDaoInterface> {

    Teacher findStudentsWithTeacher(Integer id);

    Teacher findCoursesWithTeacher(Integer id);
}
