package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class SubTopic {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("subTopic")
    private String subTopic;

    @JsonProperty("notes")
    private List<String> notes;

}
