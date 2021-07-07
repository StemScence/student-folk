package com.stemscence.studentsapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stemscence.studentsapp.dto.*;
import com.stemscence.studentsapp.repositories.AssignmentRepository;
import com.stemscence.studentsapp.repositories.ExamRepository;
import com.stemscence.studentsapp.repositories.StudentRepository;
import com.stemscence.studentsapp.requests.ClassRequest;
import com.stemscence.studentsapp.requests.SignupRequest;
import com.stemscence.studentsapp.responses.SignupResponse;
import com.stemscence.studentsapp.responses.StudentPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class StudentsService {
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExamRepository examRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String TEACHERS_URL = "http://TEACHERS-APP/teachers/";


    public StudentsService(StudentRepository studentRepository, AssignmentRepository assignmentRepository, ExamRepository examRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.examRepository = examRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    public ResponseEntity<SignupResponse> signup(SignupRequest signupRequest) {
       Optional<Student> student = studentRepository.findOneByPhone(signupRequest.getPhone());
       SignupResponse signupResponse = new SignupResponse();
       if (student.isPresent()){
           signupResponse.setStudent(null);
           signupResponse.setMessage("Student already exist. Head to login..");
       }

            Student student1 = new Student();
            student1.setPhone(signupRequest.getPhone());
            student1.setFirstName(signupRequest.getFirstName());
            student1.setSecondName(signupRequest.getSecondName());
            student1.setPassword(signupRequest.getPassword());

            signupResponse.setStudent(studentRepository.save(student1));

        return ResponseEntity.ok(signupResponse);
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    public ResponseEntity<Student> getStudentById(String id) {
        return ResponseEntity.ok(fetchStudentById(id).get());
    }

    public ResponseEntity<Student> updateStudent(String id, Student student) {
        Optional<Student> optionalStudent = fetchStudentById(id);
        if (optionalStudent.isPresent()){
            log.info("Updating student..");
            Student student1 = optionalStudent.get();
            student1.setDateOfBirth(student.getDateOfBirth());
            Map<String,List<String>> elcs = student1.getElcs();
            elcs.putAll(student.getElcs());
            student1.setElcs(elcs);

            List<String> subjects = student1.getSubjects();
            subjects.addAll(student.getSubjects());
            student1.setSubjects(subjects);
            student1.setEmail(student.getEmail());
            student1.setPhone(student.getPhone());
            student1.setFirstName(student.getFirstName());
            student1.setSecondName(student.getSecondName());
            return ResponseEntity.ok(student1);
        }
        log.info("Student with id {} not found..",id);
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<Student> addSubject(String id, List<String> subjects) {
        Optional<Student> student = fetchStudentById(id);
        if (student.isPresent()){
            log.info("Adding subject..");
            List<String> subjects1 = student.get().getSubjects();
            subjects1.addAll(student.get().getSubjects());
            student.get().setSubjects(subjects1);
            return ResponseEntity.ok(student.get());
        }
        log.info("Student with id {} not found",id);
        return ResponseEntity.badRequest().body(null);
    }
    public ResponseEntity<Student> addElcs(String id, Map<String,List<String>> elcs) {
        Optional<Student> student = fetchStudentById(id);
        if (student.isPresent()){
            log.info("Adding Elcs.");
            Map<String,List<String>> elcs1 = student.get().getElcs();
            elcs1.putAll(elcs);
            student.get().setElcs(elcs1);
            return ResponseEntity.ok(studentRepository.save(student.get()));
        }
        log.info("Student with id {} not found",id);
        return ResponseEntity.badRequest().body(null);
    }
    public Optional<Student> fetchStudentById(String id){
        return studentRepository.findById(id);
    }
    public ResponseEntity<StudentPayload>  getStudentPayload(String subjectId){
        //ResponseEntity<StudentPayload> studentPayloadResponseEntity = restTemplate

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<Lessons>> getClasses(ClassRequest classRequest) {
        log.info("Getting classes");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ResponseEntity<List<Lessons>> lessonsResponseEntity = null;
        try {
            Date date = formatter.parse(formatter.format(classRequest.getOnDate()));
            classRequest.setOnDate(date);
            HttpEntity<ClassRequest> request = new HttpEntity<>(classRequest);
           lessonsResponseEntity = restTemplate.exchange(
                   TEACHERS_URL + "classes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Lessons>>() {
                   });

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lessonsResponseEntity;
    }

    public ResponseEntity<List<Subjects>> getSubjects() {
        return restTemplate.exchange(TEACHERS_URL+"subjects",HttpMethod.GET
                ,null,new ParameterizedTypeReference<List<Subjects>>(){});
    }

    public ResponseEntity<Subjects> getSubjectsById(String id) {
        return restTemplate.getForEntity(TEACHERS_URL+id,Subjects.class);
    }

    public ResponseEntity<List<Assignment>> getAllAssignments(String teacherId_subjectId) {
        log.info("Getting all assignments..");
        HttpEntity<String> request = new HttpEntity<>(teacherId_subjectId);
        return restTemplate.exchange(TEACHERS_URL+"assignments",HttpMethod.GET,request,new ParameterizedTypeReference<List<Assignment>>(){});
    }
    public ResponseEntity<String> submitAssignments(String assignmentId, Assignment assignment) {
        log.info("Submitting  assignments..");
        Optional<Assignment> optionalAssignment = assignmentRepository.findByIdAndStudentId(assignmentId,assignment.getStudentId());
        if (optionalAssignment.isPresent()){
            return ResponseEntity.ok("Assignment already submitted..");
        }
        assignment.setId(assignmentId);
        assignmentRepository.save(assignment);
        return ResponseEntity.ok("Successfully Submitted");
    }

    public ResponseEntity<Assignment> getAssignmentById(String assignmentId) {
        return restTemplate.exchange(TEACHERS_URL+"assignment"+assignmentId,HttpMethod.GET
                ,null,Assignment.class);
    }

    public ResponseEntity<List<Exam>> getAllExam(String teacherId_subjectId) {
        log.info("Getting all exams..");
        HttpEntity<String> request = new HttpEntity<>(teacherId_subjectId);
        return restTemplate.exchange(TEACHERS_URL+"exams",HttpMethod.GET,request,new ParameterizedTypeReference<List<Exam>>(){});

    }

    public ResponseEntity<Exam> getExamById(String examId) {
        return restTemplate.exchange(TEACHERS_URL+"exam"+examId,HttpMethod.GET
                ,null,Exam.class);
    }

    public ResponseEntity<String> submitExam(String examId, Exam exam) {
        log.info("Submitting  exam..");
        Optional<Exam> optionalExam = examRepository.findByIdAndStudentId(examId,exam.getStudentId());
        if (optionalExam.isPresent()){
            return ResponseEntity.ok("Exam already submitted..");
        }
        exam.setId(examId);
        examRepository.save(exam);
        return ResponseEntity.ok("Successfully Submitted");
    }

    public ResponseEntity<List<Teacher>> getAllTeachers(String subjectId) {
        log.info("Getting all teachers..");
        HttpEntity<String> request = new HttpEntity<>(subjectId);
        return restTemplate.exchange(TEACHERS_URL+"teachers/"+subjectId,HttpMethod.GET,null,new ParameterizedTypeReference<List<Teacher>>(){});
    }

    public ResponseEntity<Teacher> getTeacherById(String teacherId) {
        return restTemplate.exchange(TEACHERS_URL+teacherId,HttpMethod.GET
                ,null,Teacher.class);
    }
}
