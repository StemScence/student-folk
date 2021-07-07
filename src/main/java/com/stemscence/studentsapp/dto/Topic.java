package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Topic {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("subTopics")
    private List<String> subTopics;


}
