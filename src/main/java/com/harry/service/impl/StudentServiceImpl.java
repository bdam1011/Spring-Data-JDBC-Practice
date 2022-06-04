package com.harry.service.impl;

import com.harry.dao.StudentDaoInterface;
import com.harry.domain.Student;
import com.harry.service.StudentService;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl extends ServiceInterfaceImpl<Student, StudentDaoInterface> implements StudentService {

    @Override
    public Student add(Student student) {

        Student newStudent = new Student();
        newStudent.setId(student.getId());
        newStudent.setName(student.getName());
        newStudent.setAdmissionDate(student.getAdmissionDate());
        newStudent.setTeacherId(student.getTeacherId());
        return getDao().add(newStudent);
    }

    @Override
    public Student update(Student student) {
        Student result = null;
        if (student.getId() != null) {
            Student target = getDao().findOne(student.getId());
            target.setName(student.getName());
            target.setAdmissionDate(student.getAdmissionDate());
            target.setTeacherId(student.getTeacherId());

            result = getDao().update(target);
        }
        return result;
    }
}
