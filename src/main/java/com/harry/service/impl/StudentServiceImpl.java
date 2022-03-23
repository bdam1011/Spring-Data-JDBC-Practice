package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.domain.Student;
import com.harry.service.StudentService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentServiceImpl extends ServiceInterfaceImpl<Student> implements StudentService {


    @Resource
    public void setDao(DaoInterface<Student> dao) {
        super.setDao(dao);
    }

    @Override
    public Student add(Student student) {
        Student result = null;

        Student newStudent = new Student();
        newStudent.setId(student.getId());
        newStudent.setName(student.getName());
        newStudent.setAdmissionDate(student.getAdmissionDate());
        newStudent.setTeacherId(student.getTeacherId());

        int target = this.getDao().add(newStudent);
        if (target == 1) {
            result = newStudent;
            return result;
        }
        return result;
    }

    @Override
    public Student update(Student student) {
        Student result = null;
        if (student.getId() != null) {
            Student target = this.getDao().findOne(student.getId());
            target.setName(student.getName());
            target.setAdmissionDate(student.getAdmissionDate());
            target.setTeacherId(student.getTeacherId());

            int change = this.getDao().update(target);
            if (change == 1) {
                result = target;
                return result;
            }
        }
        return result;
    }
}
