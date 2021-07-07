package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Exam {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("exam")
    private String exam;

    @JsonProperty("marks")
    private String marks;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("subjectId")
    private String subjectId;
    @JsonProperty("studentId")
    private String studentId;
    @JsonProperty("teacherId")
    private String teacherId;
    @JsonProperty("cancelled")
    private boolean cancelled = false;
    @JsonProperty("questions")
    private List<String> questions;
    @JsonProperty("answers")
    private Map<String,String> answers;
    @JsonProperty("choices")
    private Map<String,List<String>> choices;
    @JsonProperty("approval")
    private Map<String,List<String>> approval;
}
