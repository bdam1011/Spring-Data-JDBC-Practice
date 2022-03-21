package com.harry.mapper;

import com.harry.domain.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {


    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("id_"));
        teacher.setName(rs.getString("name_"));
        teacher.setTeachingScore(rs.getDouble("teaching_score_"));
        teacher.setUpdateTime(rs.getTimestamp("update_time_"));
        return teacher;
    }
}
