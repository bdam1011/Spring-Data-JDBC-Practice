package com.harry.dao;


import com.harry.domain.Course;
import com.harry.domain.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class CourseDaoTest {

    @Resource
    private CourseDaoInterface dao;

    @Test
    public void testFindAll() {
        Integer expected = 5;
        List<Course> target = dao.findAll();
        Integer actual = target.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOne() {
        Course expected = new Course();
        expected.setId(2);
        expected.setName("Logic");
        expected.setFee(8787.9);
        expected.setUpdateTime(Timestamp.valueOf("2020-07-15 09:26:50"));

        Course actual = dao.findOne(2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Course course = new Course();
        course.setId(6);
        course.setName("Computer Science");
        course.setFee(8574.9);

        Course result = dao.add(course);
        Assertions.assertNotNull(result);

        Course expected = new Course();
        expected.setId(course.getId());
        expected.setName(course.getName());
        expected.setFee(course.getFee());
        expected.setUpdateTime(result.getUpdateTime());

        Course actual = dao.findOne(6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Course course = dao.findOne(3);
        course.setName("Clark");
        course.setFee(7452.4);

        Course result = dao.update(course);
        Assertions.assertNotNull(result);

        Course expected = new Course();
        expected.setId(course.getId());
        expected.setName(course.getName());
        expected.setFee(course.getFee());
        expected.setUpdateTime(result.getUpdateTime());

        Course actual = dao.findOne(3);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testDelete() {
        Course course = dao.findOne(1);
        Assertions.assertNotNull(course);

        boolean expected = true;
        boolean actual = dao.delete(1);
        Assertions.assertEquals(expected, actual);

        Course target = dao.findOne(1);
        Assertions.assertNull(target);
    }

    @Test
    public void testFindTeachersWithCourse() {
        Course expected = new Course();
        expected.setId(2);
        expected.setName("Logic");
        expected.setFee(8787.9);
        expected.setUpdateTime(Timestamp.valueOf("2020-07-15 09:26:50"));
        expected.setTeachers(new ArrayList<>());

        Teacher expectedT1 = new Teacher();
        expectedT1.setId(1);
        expectedT1.setName("John");
        expectedT1.setTeachingScore(7.6);
        expectedT1.setUpdateTime(Timestamp.valueOf("2022-05-31 09:26:50"));

        Teacher expectedT2 = new Teacher();
        expectedT2.setId(2);
        expectedT2.setName("Frank");
        expectedT2.setTeachingScore(8.2);
        expectedT2.setUpdateTime(Timestamp.valueOf("2020-06-15 09:26:50"));

        Teacher expectedT3 = new Teacher();
        expectedT3.setId(3);
        expectedT3.setName("Rose");
        expectedT3.setTeachingScore(7.9);
        expectedT3.setUpdateTime(Timestamp.valueOf("2018-01-01 09:26:50"));

        expected.getTeachers().add(expectedT1);
        expected.getTeachers().add(expectedT2);
        expected.getTeachers().add(expectedT3);

        Course actual = dao.findTeachersWithCourse(2);
        Assertions.assertEquals(expected, actual);

    }
}
