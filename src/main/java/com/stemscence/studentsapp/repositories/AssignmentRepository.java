package com.stemscence.studentsapp.repositories;

import com.stemscence.studentsapp.dto.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AssignmentRepository  extends MongoRepository<Assignment,String> {
    Optional<Assignment> findByIdAndStudentId(String assignmentId, String studentId);
}
