package com.stemscence.studentsapp.responses;

import com.stemscence.studentsapp.dto.Student;
import lombok.Data;

@Data
public class SignupResponse {
    private Student student;
    private String message;

}
