package com.harry.dao;

import com.harry.domain.Teacher;

public interface TeacherDaoInterface extends DaoInterface<Teacher> {
    Teacher findStudentsWithTeacher(Integer id);

    Teacher findCoursesWithTeacher(Integer id);
}
