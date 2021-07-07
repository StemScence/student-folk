package com.stemscence.studentsapp.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignupRequest {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("secondName")
    private String secondName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("password")
    private String password;
}
