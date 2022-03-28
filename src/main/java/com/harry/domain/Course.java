package com.harry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;

@Data
@EqualsAndHashCode
public class Course {
    private Integer id;
    private String name;
    private Double fee;
    private Timestamp updateTime;
    private List<Teacher> teachers;
}
