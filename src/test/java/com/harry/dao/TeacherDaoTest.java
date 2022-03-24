package com.harry.dao;

import com.harry.domain.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
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

}
