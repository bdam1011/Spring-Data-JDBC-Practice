package com.harry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@EqualsAndHashCode
public class Student {
    private Integer Id;
    private String name;
    private LocalDate admissionDate;
    private Timestamp updateTime;
    private Integer teacherId;
}
