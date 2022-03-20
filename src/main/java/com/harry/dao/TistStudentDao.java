package com.harry.dao;

import com.harry.domain.TistStudent;
import com.harry.domain.TistTeacher;
import com.harry.mapper.TistStuidentRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TistStudentDao {
    @Resource
    public JdbcTemplate jdbcTemplate;

    public List<TistStudent> findAll() {
        try {
            return jdbcTemplate.query("select * from tist_student",
                    new TistStuidentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public TistStudent findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select * from tist_student where student_id_=?",
                    new TistStuidentRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public TistStudent findOne(String name) {
        try {
            return jdbcTemplate.queryForObject("select * from tist_student where name_=?",
                    new TistStuidentRowMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addOne(TistStudent tistStudent) {
        return jdbcTemplate.update("insert into tist_student (student_id_, name_, admission_date_, teacher_id_)" +
                "values(?,?,?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, tistStudent.getStudentId());
                ps.setString(2, tistStudent.getName());
                ps.setDate(3, tistStudent.getAdmissionDate());
                ps.setObject(4, tistStudent.getTeacherId());
            }
        });
    }

    public int updateOneById(TistStudent tistStudent) {
        return jdbcTemplate.update("update tist_student set name_=?,admission_date_=?,teacher_id_=?" +
                " where student_id_=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, tistStudent.getName());
                ps.setDate(2, tistStudent.getAdmissionDate());
                ps.setObject(3, tistStudent.getTeacherId());
                ps.setInt(4, tistStudent.getStudentId());
            }
        });
    }

    public int deleteOneById(Integer id) {
        return jdbcTemplate.update("delete from  tist_student where student_id_=?", id);

    }


}
