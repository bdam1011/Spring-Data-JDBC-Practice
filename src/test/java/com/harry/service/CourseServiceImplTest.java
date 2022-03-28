package com.harry.service;


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
public class CourseServiceImplTest {

    @Resource
    private CourseService courseService;

    @Test
    public void testFindAll() {
        Integer expected = 5;
        List<Course> target = courseService.findAll();
        Integer actual = target.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOne() {
        Course expected = new Course();
        expected.setId(1);
        expected.setName("Math");
        expected.setFee(432.6);
        expected.setUpdateTime(Timestamp.valueOf("2022-05-31 09:26:50"));

        Course actual = courseService.findOne(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Course newCourse = new Course();
        newCourse.setId(6);
        newCourse.setName("Geography");
        newCourse.setFee(8858.9);
        Course result = courseService.add(newCourse);
        Assertions.assertNotNull(result);

        Course expected = new Course();
        expected.setId(newCourse.getId());
        expected.setName(newCourse.getName());
        expected.setFee(newCourse.getFee());
        expected.setUpdateTime(result.getUpdateTime());

        Course actual = courseService.findOne(6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Course target = courseService.findOne(3);
        target.setName("Music");
        target.setFee(7445.4);
        Course result = courseService.update(target);
        Assertions.assertNotNull(result);

        Course expected = new Course();
        expected.setId(target.getId());
        expected.setName(target.getName());
        expected.setFee(target.getFee());
        expected.setUpdateTime(result.getUpdateTime());

        Course actual = courseService.findOne(3);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testDelete() {
        boolean expected = true;
        boolean actual = courseService.delete(1);
        Assertions.assertEquals(expected, actual);
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

        Course actual = courseService.findTeachersWithCourse(2);
        Assertions.assertEquals(expected, actual);
    }
}
