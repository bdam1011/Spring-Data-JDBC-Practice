package com.harry.dao;


import com.harry.domain.TistStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class TistStudentDaoTest {

    @Resource
    private TistStudentDao studentDao;

    @Test
    public void testFindAll(){
        Integer expected = 3;
        List<TistStudent> target = studentDao.findAll();
        Integer actual = target.size();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testFindOneById(){
        Integer expected = 2;
        TistStudent target = studentDao.findOne(2);
        Integer actual = target.getStudentId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOneByNmae(){
        String expected = "Paul";
        TistStudent target = studentDao.findOne("Paul");
        String actual = target.getName();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testAddOne(){
        TistStudent student = new TistStudent();
        student.setStudentId(4);
        student.setName("Louis");
        student.setAdmissionDate(Date.valueOf("2022-03-18"));
        student.setTeacherId(3);
        int result = studentDao.addOne(student);
        if(result==1){
            String expected = student.getName();
            TistStudent target = studentDao.findOne(4);
            String actual = target.getName();
            Assertions.assertEquals(expected,actual);
        }else{
            System.out.println("Wrong change");
        }
    }

    @Test
    public void testUpdateOneById(){
        TistStudent student = studentDao.findOne(2);
        student.setName("Bazz");
        student.setAdmissionDate(Date.valueOf("2022-03-21"));
        int result = studentDao.updateOneById(student);
        if(result==1){
            Date expected = student.getAdmissionDate();
            TistStudent target = studentDao.findOne(2);
            Date actual = target.getAdmissionDate();
            Assertions.assertEquals(expected,actual);
        }else{
            System.out.println("Change the wrong row");
        }

    }

    @Test
    public void testDeleteOneById(){
        TistStudent student = studentDao.findOne("Frank");
        if(student!=null){
            int result = studentDao.deleteOneById(student.getStudentId());
            if(result==1){
                TistStudent target = studentDao.findOne("Frank");
                Assertions.assertNull(target);
            }else{
                System.out.println("Wrong change");
            }
        }else{
            System.out.println("The row do not exist!");
        }
    }



}
