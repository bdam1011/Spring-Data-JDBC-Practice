package com.harry.service;

import com.harry.domain.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class TeacherServiceImplTest {

    @Resource
    private TeacherService teacherService;

    @Test
    public void testFindAll() {
        Integer expected = 3;
        List<Teacher> target = teacherService.findAll();
        Integer actual = target.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOne() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setName("John");
        expected.setTeachingScore(7.6);

        Teacher actual = teacherService.findOne(1);
        actual.setUpdateTime(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd(){
        Teacher newTeacher = new Teacher();
        newTeacher.setId(4);
        newTeacher.setName("Mary");
        newTeacher.setTeachingScore(8.9);
        Teacher result = teacherService.add(newTeacher);
        Assertions.assertNotNull(result);

        Teacher expected = new Teacher();
        expected.setId(newTeacher.getId());
        expected.setName(newTeacher.getName());
        expected.setTeachingScore(newTeacher.getTeachingScore());

        Teacher actual = teacherService.findOne(4);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate(){
        Teacher target = teacherService.findOne(3);
        target.setName("Clark");
        target.setTeachingScore(7.4);
        Teacher result = teacherService.update(target);
        Assertions.assertNotNull(result);

        Teacher expected = new Teacher();
        expected.setId(target.getId());
        expected.setName(target.getName());
        expected.setTeachingScore(target.getTeachingScore());

        Teacher actual = teacherService.findOne(3);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testDelete() {
        boolean expected = true;
        boolean actual = teacherService.delete(1);
        Assertions.assertEquals(expected, actual);
    }

}
