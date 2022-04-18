package com.challenge.school.modules.student.controllers;

import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;
import com.challenge.school.modules.student.usecases.CreateStudentUseCase;
import com.challenge.school.modules.student.usecases.GetStudentByEnrollmentUseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${api.prefix}/${api.version}/students")
public class StudentController implements StudentControllerDocs {
    private final CreateStudentUseCase createStudentUseCase;
    private final GetStudentByEnrollmentUseCase getStudentByEnrollmentUseCase;

    @Override
    @PostMapping
    public ResponseEntity<StudentResponse> handleSave(@RequestBody StudentRequest data) {
        StudentResponse response = createStudentUseCase.execute(data);

        return ResponseEntity
                .status(CREATED)
                .contentType(APPLICATION_JSON)
                .body(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<StudentResponse> handleGetByEnrollment(@RequestParam String enrollment) {
        StudentResponse response = getStudentByEnrollmentUseCase.execute(enrollment);

        return ResponseEntity
                .status(OK)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
