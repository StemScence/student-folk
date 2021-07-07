package com.stemscence.studentsapp.repositories;

import com.stemscence.studentsapp.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findOneByPhone(String phone);
}
