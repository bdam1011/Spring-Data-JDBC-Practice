package com.harry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@Data
@EqualsAndHashCode
public class Teacher {

    private Integer id;
    private String name;
    private Double teachingScore;
    private Timestamp updateTime;

}
