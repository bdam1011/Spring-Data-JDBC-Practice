package com.harry.mapper;

import com.harry.domain.TistTeacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TistTeacherRowMapper implements RowMapper<TistTeacher> {


    @Override
    public TistTeacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        TistTeacher teacher = new TistTeacher();
        teacher.setTeacherId(rs.getInt("teacher_id_"));
        teacher.setName(rs.getString("name_"));
        teacher.setTeachingScore(rs.getDouble("teaching_score_"));
        teacher.setUpdateTime(rs.getTimestamp("update_time_"));
        return teacher;
    }
}
