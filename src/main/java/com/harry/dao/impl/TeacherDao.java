package com.harry.dao.impl;

import com.harry.dao.TeacherDaoInterface;
import com.harry.domain.Course;
import com.harry.domain.Student;
import com.harry.domain.Teacher;
import com.harry.mapper.TeacherRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        int target = jdbcTemplate.update("insert into tist_teacher (id_, name_, teaching_score_) values(?,?,?)",
                teacher.getId(), teacher.getName(), teacher.getTeachingScore());
        if (target == 1) {
            result = this.findOne(teacher.getId());
        }
        return result;
    }

    @Override
    public Teacher update(Teacher teacher) {
        Teacher result = null;
        int target = jdbcTemplate.update("update tist_teacher set name_=?,teaching_score_=?where id_=?",
                teacher.getName(), teacher.getTeachingScore(), teacher.getId());
        if (target == 1) {
            result = this.findOne(teacher.getId());
        }
        return result;
    }

    @Override
    public Teacher findStudentsWithTeacher(Integer id) {

        return jdbcTemplate.query(
                "select " +
                        "t.id_ ,t.name_ ,t.teaching_score_,t.update_time_ ," +
                        "s.id_ sid ,s.name_ sname,s.admission_date_ sdate,s.update_time_ supdate  " +
                        "from tist_teacher t " +
                        "left join tist_student s " +
                        "on t.id_ = fk_tist_teacher_ where t.id_=?", new ResultSetExtractor<Teacher>() {
                    @Override
                    public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Teacher teacher = null;
                        while (rs.next()) {
                            if (teacher == null) {
                                teacher = new Teacher();
                                teacher.setId(rs.getInt("id_"));
                                teacher.setName(rs.getString("name_"));
                                teacher.setTeachingScore(rs.getDouble("teaching_score_"));
                                teacher.setUpdateTime(rs.getTimestamp("update_time_"));
                                teacher.setStudents(new ArrayList<>());
                            }
                            Student student = new Student();
                            student.setId(rs.getInt("sid"));
                            student.setName(rs.getString("sname"));
                            student.setUpdateTime(rs.getTimestamp("supdate"));
                            student.setAdmissionDate(rs.getDate("sdate").toLocalDate());
                            student.setTeacherId(rs.getInt("id_"));
                            teacher.getStudents().add(student);
                        }

                        return teacher;
                    }
                }, id);
    }

    @Override
    public Teacher findCoursesWithTeacher(Integer id) {
        return jdbcTemplate.query(
                "select  " +
                        "t.id_ ,t.name_ ,t.teaching_score_,t.update_time_ ," +
                        "c.id_ cid, c.name_ cname,c.fee_ cfee ,c.update_time_ cupdate " +
                        "from tist_teacher t " +
                        "inner join tist_teacher_course m " +
                        "on t.id_ = m.fk_tist_teacher_ " +
                        "left join tist_course c " +
                        "on m.fk_tist_course_ = c.id_ " +
                        "where t.id_ =?", new ResultSetExtractor<Teacher>() {
                    @Override
                    public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Teacher teacher = null;
                        while (rs.next()) {
                            if (teacher == null) {
                                teacher = new Teacher();
                                teacher.setId(rs.getInt("id_"));
                                teacher.setName(rs.getString("name_"));
                                teacher.setTeachingScore(rs.getDouble("teaching_score_"));
                                teacher.setUpdateTime(rs.getTimestamp("update_time_"));
                                teacher.setCourses(new ArrayList<>());
                            }
                            Course course = new Course();
                            course.setId(rs.getInt("cid"));
                            course.setName(rs.getString("cname"));
                            course.setUpdateTime(rs.getTimestamp("cupdate"));
                            course.setFee(rs.getDouble("cfee"));
                            teacher.getCourses().add(course);
                        }

                        return teacher;
                    }
                }, id);
    }

}
