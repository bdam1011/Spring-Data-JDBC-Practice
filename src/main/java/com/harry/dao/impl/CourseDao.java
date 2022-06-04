package com.harry.dao.impl;

import com.harry.dao.CourseDaoInterface;
import com.harry.domain.Course;
import com.harry.domain.Teacher;
import com.harry.mapper.CourseRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CourseDao extends AbstractDao<Course> implements CourseDaoInterface {

    public CourseDao() {
        setRowMapper(new CourseRowMapper());
        setTableName("tist_course");
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Course add(Course course) {
        Course result = null;
        int target = jdbcTemplate.update("insert into tist_course (id_, name_, fee_) values(?,?,?)",
                course.getId(), course.getName(), course.getFee());
        if (target == 1) {
            result = this.findOne(course.getId());
        }
        return result;
    }

    @Override
    public Course update(Course course) {
        Course result = null;
        int target = jdbcTemplate.update("update tist_course set name_=?,fee_=?where id_=?",
                course.getName(), course.getFee(), course.getId());
        if (target == 1) {
            result = this.findOne(course.getId());
        }
        return result;
    }

    @Override
    public Course findTeachersWithCourse(Integer id) {

        return jdbcTemplate.query(
                "select  " +
                        "t.id_ ,t.name_ ,t.teaching_score_,t.update_time_ ," +
                        "c.id_ cid, c.name_ cname,c.fee_ cfee ,c.update_time_ cupdate " +
                        "from tist_course c " +
                        "inner join tist_teacher_course m " +
                        "on m.fk_tist_course_ = c.id_ " +
                        "inner join tist_teacher t " +
                        "on t.id_ = m.fk_tist_teacher_ " +
                        "where c.id_ =?", new ResultSetExtractor<Course>() {
                    @Override
                    public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Course course = null;
                        while (rs.next()) {
                            if (course == null) {
                                course = new Course();
                                course.setId(rs.getInt("cid"));
                                course.setName(rs.getString("cname"));
                                course.setUpdateTime(rs.getTimestamp("cupdate"));
                                course.setFee(rs.getDouble("cfee"));
                                course.setTeachers(new ArrayList<>());
                            }
                            Teacher teacher = new Teacher();
                            teacher.setId(rs.getInt("id_"));
                            teacher.setName(rs.getString("name_"));
                            teacher.setTeachingScore(rs.getDouble("teaching_score_"));
                            teacher.setUpdateTime(rs.getTimestamp("update_time_"));
                            course.getTeachers().add(teacher);
                        }

                        return course;
                    }
                }, id);
    }

}
