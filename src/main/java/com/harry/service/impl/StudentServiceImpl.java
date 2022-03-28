package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.domain.Student;
import com.harry.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StudentServiceImpl extends ServiceInterfaceImpl<Student> implements StudentService {


    @Resource
    public void setDao(DaoInterface<Student> dao) {
        super.setDao(dao);
    }

    @Override
    public Student add(Student student) {

        Student newStudent = new Student();
        newStudent.setId(student.getId());
        newStudent.setName(student.getName());
        newStudent.setAdmissionDate(student.getAdmissionDate());
        newStudent.setTeacherId(student.getTeacherId());
        return this.getDao().add(newStudent);
    }

    @Override
    public Student update(Student student) {
        Student result = null;
        if (student.getId() != null) {
            Student target = this.getDao().findOne(student.getId());
            target.setName(student.getName());
            target.setAdmissionDate(student.getAdmissionDate());
            target.setTeacherId(student.getTeacherId());

            result = this.getDao().update(target);
        }
        return result;
    }
}
