package com.harry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;


@Data
@EqualsAndHashCode
public class Teacher {

    private Integer Id;
    private String name;
    private Double teachingScore;
    private Timestamp updateTime;
    private List<Student> students;
    private List<Course> courses;

}
