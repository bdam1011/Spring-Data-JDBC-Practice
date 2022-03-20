package com.harry.domain;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;


@Data
public class TistStudent {
    private Integer studentId;
    private String name;
    private Date admissionDate;
    private Timestamp updateTime;
    private Integer teacherId;
}
