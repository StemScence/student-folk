package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Subjects {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("topics")
    private List<String> topics;

    @JsonProperty("assignments")
    private List<String> assignments;

    @JsonProperty("reports")
    private  List<String> reports;

    @JsonProperty("exams")
    private List<String> exams;

    @JsonProperty("average")
    private String average;

    @JsonProperty("teacherId")
    private String teacherId;


}
