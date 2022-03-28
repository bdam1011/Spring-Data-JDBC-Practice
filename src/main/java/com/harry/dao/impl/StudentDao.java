package com.harry.dao.impl;

import com.harry.dao.StudentDaoInterface;
import com.harry.domain.Student;
import com.harry.mapper.StudentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentDao extends AbstractDao<Student> implements StudentDaoInterface {

    public StudentDao() {
        setRowMapper(new StudentRowMapper());
        setTableName("tist_student");
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Student add(Student student) {
        Student result = null;
        int target = jdbcTemplate.update("insert into tist_student (id_, name_, admission_date_, fk_tist_teacher_)" +
                        "values(?,?,?,?)", student.getId(), student.getName(),
                java.sql.Date.valueOf(student.getAdmissionDate()), student.getTeacherId());
        if (target == 1) {
            result = this.findOne(student.getId());
        }
        return result;
    }

    @Override
    public Student update(Student student) {
        Student result = null;
        int target = jdbcTemplate.update("update tist_student set name_=?,admission_date_=?,fk_tist_teacher_=?" +
                        " where id_=?", student.getName(), java.sql.Date.valueOf(student.getAdmissionDate()),
                student.getTeacherId(), student.getId());
        if (target == 1) {
            result = this.findOne(student.getId());
        }
        return result;

    }


}
