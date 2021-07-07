package com.stemscence.studentsapp.repositories;

import com.stemscence.studentsapp.dto.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExamRepository  extends MongoRepository<Exam, String> {
    Optional<Exam> findByIdAndStudentId(String examId, String studentId);
}
