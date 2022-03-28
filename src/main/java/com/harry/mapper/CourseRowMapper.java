package com.harry.mapper;

import com.harry.domain.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id_"));
        course.setName(rs.getString("name_"));
        course.setFee(rs.getDouble("fee_"));
        course.setUpdateTime(rs.getTimestamp("update_time_"));
        return course;
    }
}
