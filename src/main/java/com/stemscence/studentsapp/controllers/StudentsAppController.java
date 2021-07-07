package com.stemscence.studentsapp.controllers;

import com.stemscence.studentsapp.dto.*;
import com.stemscence.studentsapp.requests.ClassRequest;
import com.stemscence.studentsapp.requests.SignupRequest;
import com.stemscence.studentsapp.responses.SignupResponse;
import com.stemscence.studentsapp.services.StudentsService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("students")
@Api(tags = "Students controller")
@CrossOrigin
public class StudentsAppController {
    private final StudentsService studentsService;

    public StudentsAppController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest){
        return studentsService.signup(signupRequest);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return studentsService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id){
        return studentsService.getStudentById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student){
        return studentsService.updateStudent(id,student);
    }
    @PostMapping("/addSubject/{id}")
    public ResponseEntity<Student> addSubject(@PathVariable String id, @RequestBody List<String> subjects){
        return studentsService.addSubject(id,subjects);
    }
    @PostMapping("/addElc/{id}")
    public ResponseEntity<Student> addElcs(@PathVariable String id, @RequestBody Map<String,List<String>> subjects){
        return studentsService.addElcs(id,subjects);
    }

    //get classes
    @GetMapping("/classes")
    public ResponseEntity<List<Lessons>> getClasses(@RequestBody ClassRequest request){
        return studentsService.getClasses(request);
    }

    //get subject
    @GetMapping("/subjects")
    public ResponseEntity<List<Subjects>> getSubjectsById(){
        return studentsService.getSubjects();
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subjects> getSubjectsById(@PathVariable String id){
        return studentsService.getSubjectsById(id);
    }


    //get assignment
    @GetMapping("/assignments/{teacherId_subjectId}")
    public ResponseEntity<List<Assignment>> getAssignments(@PathVariable String teacherId_subjectId){
        return studentsService.getAllAssignments(teacherId_subjectId);
    }
    @GetMapping("/assignments/{assignmentId}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable String assignmentId) {
        return studentsService.getAssignmentById(assignmentId);
    }
    @PostMapping("/assignment/submit/{assignmentId}")
    public ResponseEntity<String> submitAssignments(@PathVariable String assignmentId, @RequestBody Assignment assignment){
        return studentsService.submitAssignments(assignmentId,assignment);
    }
    //get exam
    @GetMapping("/exams/{teacherId_subjectId}")
    public ResponseEntity<List<Exam>> getAllExam(@PathVariable  String teacherId_subjectId){
        return studentsService.getAllExam( teacherId_subjectId);
    }
    @GetMapping("/exams/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable String examId){
        return studentsService.getExamById(examId);
    }
    @PostMapping("exams/submit/{examId}")
    public ResponseEntity<String> submitExam(@PathVariable String examId, @RequestBody Exam exam){
        return studentsService.submitExam(examId,exam);
    }

    //get Teacher
    @GetMapping("/teachers/{subjectId}")
    public ResponseEntity<List<Teacher>> getAllTeachers(@PathVariable String subjectId){
        return studentsService.getAllTeachers(subjectId);
    }
    @GetMapping("/teachers/id/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId){
        return studentsService.getTeacherById(teacherId);
    }
}
