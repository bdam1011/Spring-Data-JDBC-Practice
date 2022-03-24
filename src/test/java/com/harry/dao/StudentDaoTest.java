package com.harry.dao;


import com.harry.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class StudentDaoTest {

    @Resource
    private StudentDaoInterface dao;

    @Test
    public void testFindAll() {
        Integer expected = 3;
        List<Student> target = dao.findAll();
        Integer actual = target.size();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testFindOne() {
        Student expected = new Student();
        expected.setId(2);
        expected.setName("Frank");
        expected.setAdmissionDate(LocalDate.of(2022, 03, 10));
        expected.setUpdateTime(Timestamp.valueOf("2020-06-15 09:26:50"));
        expected.setTeacherId(1);

        Student actual = dao.findOne(2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Student student = new Student();
        student.setId(4);
        student.setName("Louis");
        student.setAdmissionDate(LocalDate.of(2022, 03, 22));
        student.setTeacherId(3);

        Student result = dao.add(student);
        Assertions.assertNotNull(result);

        Student expected = new Student();
        expected.setId(student.getId());
        expected.setName(student.getName());
        expected.setAdmissionDate(student.getAdmissionDate());
        expected.setUpdateTime(result.getUpdateTime());
        expected.setTeacherId(student.getTeacherId());

        Student actual = dao.findOne(4);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testUpdate() {
        Student student = dao.findOne(2);
        student.setName("Bazz");
        student.setAdmissionDate(LocalDate.of(2022, 01, 01));
        student.setTeacherId(3);

        Student result = dao.update(student);
        Assertions.assertNotNull(result);


        Student expected = new Student();
        expected.setId(student.getId());
        expected.setName(student.getName());
        expected.setAdmissionDate(student.getAdmissionDate());
        expected.setUpdateTime(result.getUpdateTime());
        expected.setTeacherId(student.getTeacherId());

        Student actual = dao.findOne(2);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testDelete() {
        Student student = dao.findOne(2);
        Assertions.assertNotNull(student);

        boolean expected = true;
        boolean actual = dao.delete(student.getId());
        Assertions.assertEquals(expected, actual);

        Student target = dao.findOne(2);
        Assertions.assertNull(target);
    }

}
