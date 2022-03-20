package com.harry.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
public class TistTeacher {

    private Integer teacherId;
    private String name;
    private Double teachingScore;
    private Timestamp updateTime;

}
