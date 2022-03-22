package com.harry.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;


@Data
public class Student {
    private Integer Id;
    private String name;
    private LocalDate admissionDate;
    private Timestamp updateTime;
    private Integer teacherId;
}
