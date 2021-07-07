package com.stemscence.studentsapp.responses;

import com.stemscence.studentsapp.dto.*;
import lombok.Data;

import java.util.List;

@Data
public class StudentPayload {
    private List<Assignment> assignments;
    private List<Exam> exams;
    private Elc elc;
    private Lessons lessons;
    private List<Reports> reports;
    private Teacher teacher;
    private List<Topic> topics;
    private List<SubTopic> subTopics;

}
