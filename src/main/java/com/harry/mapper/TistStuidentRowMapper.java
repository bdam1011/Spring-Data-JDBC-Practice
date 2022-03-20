package com.harry.mapper;

import com.harry.dao.TistTeacherDao;
import com.harry.domain.TistStudent;
import com.harry.domain.TistTeacher;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TistStuidentRowMapper implements RowMapper<TistStudent> {

    @Override
    public TistStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
        TistStudent student = new TistStudent();

        student.setStudentId(rs.getInt("student_id_"));
        student.setName(rs.getString("name_"));
        student.setUpdateTime(rs.getTimestamp("update_time_"));
        student.setAdmissionDate(rs.getDate("admission_date_"));
        student.setTeacherId(rs.getInt("teacher_id_"));
        return student;
    }
}
