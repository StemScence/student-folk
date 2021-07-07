package com.stemscence.studentsapp.requests;

import lombok.Data;

import java.util.Date;
@Data
public class ClassRequest {
    private String subjectId;
    private String teacherId;
    private Date onDate;
}
