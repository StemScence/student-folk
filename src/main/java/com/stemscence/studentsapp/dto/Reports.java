package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Reports {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("report")
    private String report;

    @JsonProperty("teacherId")
    private String teacherId;

    @JsonProperty("subjectId")
    private String subjectId;

    @JsonProperty("date")
    private Date date;
    @JsonProperty
    private String studentId;
}
