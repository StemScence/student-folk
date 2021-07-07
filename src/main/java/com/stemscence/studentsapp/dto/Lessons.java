package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.util.Date;

@Data
public class Lessons {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("classes")
    private String classes;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("teacher")
    private String  teacherId;


    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("cancelled")
    private boolean cancelled = false;


}
