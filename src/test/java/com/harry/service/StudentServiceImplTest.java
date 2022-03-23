package com.harry.service;

import com.harry.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {
    @Resource
    private StudentService studentService;

    @Test
    public void testFindAll() {
        Integer expected = 3;
        List<Student> target = studentService.findAll();
        Integer actual = target.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOne() {
        Student expected = new Student();
        expected.setId(2);
        expected.setName("Frank");
        expected.setAdmissionDate(LocalDate.of(2022, 03, 10));
        expected.setTeacherId(1);

        Student actual = studentService.findOne(2);
        actual.setUpdateTime(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAdd(){
        Student newStudent = new Student();
        newStudent.setId(4);
        newStudent.setName("Louis");
        newStudent.setAdmissionDate(LocalDate.of(2022, 03, 22));
        newStudent.setTeacherId(2);
        Student result = studentService.add(newStudent);
        Assertions.assertNotNull(result);

        Student expected = new Student();
        expected.setId(newStudent.getId());
        expected.setName(newStudent.getName());
        expected.setAdmissionDate(newStudent.getAdmissionDate());
        expected.setTeacherId(newStudent.getTeacherId());

        Student actual = studentService.findOne(4);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testUpdate(){
        Student student = studentService.findOne(2);
        student.setName("Bazz");
        student.setAdmissionDate(LocalDate.of(2022, 01, 01));
        student.setTeacherId(3);
        Student result = studentService.update(student);
        Assertions.assertNotNull(result);

        Student expected = new Student();
        expected.setId(student.getId());
        expected.setName(student.getName());
        expected.setAdmissionDate(student.getAdmissionDate());
        expected.setTeacherId(student.getTeacherId());

        Student actual = studentService.findOne(2);
        actual.setUpdateTime(null);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testDelete(){
        boolean expected = true;
        boolean actual = studentService.delete(1);
        Assertions.assertEquals(expected, actual);
    }
}
