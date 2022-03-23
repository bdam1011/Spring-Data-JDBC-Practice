package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.domain.Teacher;
import com.harry.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl extends ServiceInterfaceImpl<Teacher> implements TeacherService {

    @Resource
    public void setDao(DaoInterface<Teacher> dao) {
        super.setDao(dao);
    }

    @Override
    public Teacher add(Teacher teacher) {

        Teacher result = null;

        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacher.getId());
        newTeacher.setName(teacher.getName());
        newTeacher.setTeachingScore(teacher.getTeachingScore());

        int target = this.getDao().add(newTeacher);
        if (target == 1) {
            result = newTeacher;
            return result;
        }

        return result;
    }

    @Override
    public Teacher update(Teacher teacher) {

        Teacher result = null;
        if (teacher.getId() != null) {
            Teacher target = this.getDao().findOne(teacher.getId());
            target.setName(teacher.getName());
            target.setTeachingScore(teacher.getTeachingScore());

            int change = this.getDao().update(target);
            if (change == 1) {
                result = target;
                return result;
            }
        }
        return result;
    }


}
