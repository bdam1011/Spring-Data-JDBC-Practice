package com.harry.dao;

import com.harry.domain.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

        Teacher actual = dao.findOne(1);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Teacher teacher = new Teacher();
        teacher.setId(4);
        teacher.setName("Mary");
        teacher.setTeachingScore(8.9);
        Integer changeRowExpected = 1;
        int result = dao.add(teacher);
        Assertions.assertEquals(changeRowExpected, result);

        Teacher expected = new Teacher();
        expected.setId(teacher.getId());
        expected.setName(teacher.getName());
        expected.setTeachingScore(teacher.getTeachingScore());

        Teacher actual = dao.findOne(4);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Teacher teacher = dao.findOne(3);
        teacher.setName("Clark");
        teacher.setTeachingScore(7.4);
        Integer changeRowExpected = 1;
        int result = dao.update(teacher);
        Assertions.assertEquals(changeRowExpected, result);

        Teacher expected = new Teacher();
        expected.setId(teacher.getId());
        expected.setName(teacher.getName());
        expected.setTeachingScore(teacher.getTeachingScore());

        Teacher actual = dao.findOne(3);
        actual.setUpdateTime(null);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testDelete() {
        Teacher teacher = dao.findOne(1);
        Assertions.assertNotNull(teacher);
        Integer changeRowExpected = 1;
        int result = dao.delete(1);
        Assertions.assertEquals(changeRowExpected, result);

        Teacher target = dao.findOne(1);
        Assertions.assertNull(target);
    }

}
