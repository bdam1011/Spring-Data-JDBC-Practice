package com.harry.dao;

import com.harry.domain.TistTeacher;
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
public class TistTeacherDaoTest {
    @Resource
    private TistTeacherDao teacherDao;


    @Test
    public void testFindAll(){
        Integer expected = 3;
        List<TistTeacher> target = teacherDao.findAll();
        Integer actual = target.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOneById() {
        Integer expected = 1;
        TistTeacher target = teacherDao.findOne(1);
        Integer actual = target.getTeacherId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOneByName() {
        String expected = "John";
        TistTeacher target = teacherDao.findOne("John");
        String actual = target.getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddOne() {
        TistTeacher teacher = new TistTeacher();
        teacher.setTeacherId(4);
        teacher.setName("Mary");
        teacher.setTeachingScore(8.9);
        int result = teacherDao.addOne(teacher);
        if (result == 1) {
            String expected = teacher.getName();
            TistTeacher target = teacherDao.findOne(4);
            String actual = target.getName();
            Assertions.assertEquals(expected, actual);
        }else{
            System.out.println("Wrong change");
        }

    }

    @Test
    public void testUpdateById() {
        TistTeacher teacher = teacherDao.findOne(3);
        teacher.setName("Clark");
        teacher.setTeachingScore(7.4);
        int result = teacherDao.updateOneById(teacher);
        if (result == 1) {
            String expected = teacher.getName();
            TistTeacher target = teacherDao.findOne(3);
            String actual = target.getName();
            Assertions.assertEquals(expected, actual);
        }else{
            System.out.println("Wrong change");
        }

    }

    @Test
    public void testDeleteById() {
        TistTeacher teacher = teacherDao.findOne(1);
        if (teacher != null) {
            int result = teacherDao.deleteOneById(1);
            if (result == 1) {
                TistTeacher target = teacherDao.findOne(1);
                Assertions.assertNull(target);
            }else{
                System.out.println("Wrong change");
            }
        }else{
            System.out.println("The row do not exist!");
        }

    }

}
