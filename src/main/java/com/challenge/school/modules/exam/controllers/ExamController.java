package com.challenge.school.modules.exam.controllers;

import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.usecases.CreateExamUseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${api.prefix}/${api.version}/exams")
public class ExamController implements ExamControllerDocs {
    private final CreateExamUseCase createExamUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ExamResponse> handleSaveExam(@RequestBody ExamRequest data) {
        ExamResponse response = createExamUseCase.execute(data);

        return ResponseEntity
                .status(CREATED)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
