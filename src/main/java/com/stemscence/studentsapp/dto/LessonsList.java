package com.stemscence.studentsapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LessonsList implements Serializable {
    List<Lessons> lessonsList;
    public LessonsList(){
        lessonsList = new ArrayList<>();
    }
}
