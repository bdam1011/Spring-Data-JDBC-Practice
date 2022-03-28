package com.harry.dao;

import com.harry.domain.Course;
import com.harry.domain.Student;
import com.harry.domain.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class TeacherDaoTest {
    @Resource
    private TeacherDaoInterface dao;


    @Test
    public void testFindAll() {
        Integer expected = 3;
        List<Teacher> target = dao.findAll();
        Integer actual = target.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOne() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setName("John");
        expected.setTeachingScore(7.6);
        expected.setUpdateTime(Timestamp.valueOf("2022-05-31 09:26:50"));

        Teacher actual = dao.findOne(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Teacher teacher = new Teacher();
        teacher.setId(4);
        teacher.setName("Mary");
        teacher.setTeachingScore(8.9);

        Teacher result = dao.add(teacher);
        Assertions.assertNotNull(result);

        Teacher expected = new Teacher();
        expected.setId(teacher.getId());
        expected.setName(teacher.getName());
        expected.setTeachingScore(teacher.getTeachingScore());
        expected.setUpdateTime(result.getUpdateTime());

        Teacher actual = dao.findOne(4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Teacher teacher = dao.findOne(3);
        teacher.setName("Clark");
        teacher.setTeachingScore(7.4);

        Teacher result = dao.update(teacher);
        Assertions.assertNotNull(result);

        Teacher expected = new Teacher();
        expected.setId(teacher.getId());
        expected.setName(teacher.getName());
        expected.setTeachingScore(teacher.getTeachingScore());
        expected.setUpdateTime(result.getUpdateTime());

        Teacher actual = dao.findOne(3);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testDelete() {
        Teacher teacher = dao.findOne(1);
        Assertions.assertNotNull(teacher);

        boolean expected = true;
        boolean actual = dao.delete(1);
        Assertions.assertEquals(expected, actual);

        Teacher target = dao.findOne(1);
        Assertions.assertNull(target);
    }

    @Test
    public void testFindStudentsWithTeacher() {
        Teacher expectedT = new Teacher();
        expectedT.setId(1);
        expectedT.setName("John");
        expectedT.setTeachingScore(7.6);
        expectedT.setUpdateTime(Timestamp.valueOf("2022-05-31 09:26:50"));
        expectedT.setStudents(new ArrayList<>());

        Student expectedS1 = new Student();
        expectedS1.setId(1);
        expectedS1.setName("Paul");
        expectedS1.setAdmissionDate(LocalDate.of(2022, 3, 18));
        expectedS1.setUpdateTime(Timestamp.valueOf("2022-05-31 09:26:50"));
        expectedS1.setTeacherId(1);

        Student expectedS2 = new Student();
        expectedS2.setId(2);
        expectedS2.setName("Frank");
        expectedS2.setAdmissionDate(LocalDate.of(2022, 3, 10));
        expectedS2.setUpdateTime(Timestamp.valueOf("2020-06-15 09:26:50"));
        expectedS2.setTeacherId(1);

        expectedT.getStudents().add(expectedS1);
        expectedT.getStudents().add(expectedS2);

        Teacher actual = dao.findStudentsWithTeacher(1);
        Assertions.assertEquals(expectedT, actual);

    }

    @Test
    public void TestFindCoursesWithTeacher() {
        Teacher expectedT = new Teacher();
        expectedT.setId(2);
        expectedT.setName("Frank");
        expectedT.setTeachingScore(8.2);
        expectedT.setUpdateTime(Timestamp.valueOf("2020-06-15 09:26:50"));
        expectedT.setCourses(new ArrayList<>());

        Course expectedS1 = new Course();
        expectedS1.setId(2);
        expectedS1.setName("Logic");
        expectedS1.setFee(8787.9);
        expectedS1.setUpdateTime(Timestamp.valueOf("2020-07-15 09:26:50"));

        Course expectedS2 = new Course();
        expectedS2.setId(4);
        expectedS2.setName("History");
        expectedS2.setFee(183.7);
        expectedS2.setUpdateTime(Timestamp.valueOf("2020-09-15 09:26:50"));

        expectedT.getCourses().add(expectedS1);
        expectedT.getCourses().add(expectedS2);

        Teacher actual = dao.findCoursesWithTeacher(2);
        Assertions.assertEquals(expectedT, actual);
    }


}
