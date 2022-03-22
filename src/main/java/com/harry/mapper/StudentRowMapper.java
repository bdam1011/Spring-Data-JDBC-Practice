package com.harry.mapper;

import com.harry.domain.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setId(rs.getInt("id_"));
        student.setName(rs.getString("name_"));
        student.setUpdateTime(rs.getTimestamp("update_time_"));
        student.setAdmissionDate(rs.getDate("admission_date_").toLocalDate());
        student.setTeacherId(rs.getInt("fk_tist_teacher_"));
        return student;
    }
}
