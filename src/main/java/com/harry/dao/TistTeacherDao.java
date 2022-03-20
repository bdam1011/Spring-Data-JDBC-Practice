package com.harry.dao;

import com.harry.domain.TistTeacher;
import com.harry.mapper.TistTeacherRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class TistTeacherDao {
    @Resource
    public JdbcTemplate jdbcTemplate;

    public List<TistTeacher> findAll() {
        try {
            return jdbcTemplate.query("select * from tist_teacher",
                    new TistTeacherRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public TistTeacher findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select *from tist_teacher where teacher_id_ = ?",
                    new TistTeacherRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public TistTeacher findOne(String name) {
        try {
            return jdbcTemplate.queryForObject("select *from tist_teacher where name_ = ?",
                    new TistTeacherRowMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addOne(TistTeacher tistTeacher) {
        return jdbcTemplate.update("insert into tist_teacher (teacher_id_, name_, teaching_score_) values(?,?,?)",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, tistTeacher.getTeacherId());
                        ps.setString(2, tistTeacher.getName());
                        ps.setDouble(3, tistTeacher.getTeachingScore());
                    }
                });
    }

    public int updateOneById(TistTeacher tistTeacher) {
        return jdbcTemplate.update("update tist_teacher set name_=?,teaching_score_=?where teacher_id_=?",
                new Object[]{tistTeacher.getName(),tistTeacher.getTeachingScore(),tistTeacher.getTeacherId()}
                );
    }


    public int deleteOneById(Integer id) {
        return jdbcTemplate.update("delete from tist_teacher where teacher_id_=?",
                id);
    }


}
