package com.harry.dao.impl;

import com.harry.dao.TeacherDaoInterface;
import com.harry.domain.Teacher;
import com.harry.mapper.TeacherRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TeacherDao extends AbstractDao<Teacher> implements TeacherDaoInterface {
    public TeacherDao() {
        setRowMapper(new TeacherRowMapper());
        setTableName("tist_teacher");
    }

    @Resource

    private JdbcTemplate jdbcTemplate;

    @Override
    public Teacher add(Teacher teacher) {
        Teacher result = null;
        int target =  jdbcTemplate.update("insert into tist_teacher (id_, name_, teaching_score_) values(?,?,?)",
                teacher.getId(),teacher.getName(),teacher.getTeachingScore());
        if(target ==1){
            result = this.findOne(teacher.getId());
        }
        return result;
    }

    @Override
    public Teacher update(Teacher teacher) {
        Teacher result = null;
        int target =  jdbcTemplate.update("update tist_teacher set name_=?,teaching_score_=?where id_=?",
                teacher.getName(), teacher.getTeachingScore(), teacher.getId());
        if(target ==1){
            result = this.findOne(teacher.getId());
        }
        return result;
    }
}
