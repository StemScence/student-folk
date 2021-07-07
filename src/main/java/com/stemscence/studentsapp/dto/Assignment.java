package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Assignment {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("assignment")
    private String assignment;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("submitDate")
    private  Date submitDate;

    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("results")
    private String results;

    @JsonProperty("comments")
    private  String comments;
    @JsonProperty("teacherId")
    private String teacherId;
    @JsonProperty("topicId")
    private String topicId;
    @JsonProperty("subjectId")
    private String subjectId;
    @JsonProperty("studentId")
    private String studentId;
    @JsonProperty("questions")
    private List<String> questions;
    @JsonProperty("choices")
    private Map<String,String[]> choices;
    @JsonProperty("answers")
    private  List<String> answers;

}
