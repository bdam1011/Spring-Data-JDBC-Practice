package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.dao.TeacherDaoInterface;
import com.harry.domain.Teacher;
import com.harry.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl extends ServiceInterfaceImpl<Teacher,TeacherDaoInterface> implements TeacherService {

    @Resource
    public TeacherDaoInterface teacherDaoInterface;

    @Override
    public Teacher add(Teacher teacher) {

        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacher.getId());
        newTeacher.setName(teacher.getName());
        newTeacher.setTeachingScore(teacher.getTeachingScore());

        return getDao().add(newTeacher);
    }

    @Override
    public Teacher update(Teacher teacher) {

        Teacher result = null;
        if (teacher.getId() != null) {
            Teacher target = getDao().findOne(teacher.getId());
            target.setName(teacher.getName());
            target.setTeachingScore(teacher.getTeachingScore());

            result = getDao().update(target);
        }
        return result;
    }


    @Override
    public Teacher findStudentsWithTeacher(Integer id) {
        return teacherDaoInterface.findStudentsWithTeacher(id);
    }

    @Override
    public Teacher findCoursesWithTeacher(Integer id) {
        return teacherDaoInterface.findCoursesWithTeacher(id);
    }
}
