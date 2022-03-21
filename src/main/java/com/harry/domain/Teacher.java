package com.harry.domain;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Teacher {

    private Integer Id;
    private String name;
    private Double teachingScore;
    private Timestamp updateTime;

}
