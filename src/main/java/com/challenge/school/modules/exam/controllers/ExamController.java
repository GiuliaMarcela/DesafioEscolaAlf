package com.challenge.school.modules.exam.controllers;

import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.usecases.CreateExamUseCase;
import com.challenge.school.modules.exam.usecases.GetExamByIdUseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${api.prefix}/${api.version}/exams")
public class ExamController implements ExamControllerDocs {
    private final CreateExamUseCase createExamUseCase;
    private final GetExamByIdUseCase getExamByIdUseCase;


    @Override
    @PostMapping
    public ResponseEntity<ExamResponse> handleSaveExam(@Valid @RequestBody ExamRequest data) {
        ExamResponse response = createExamUseCase.execute(data);

        return ResponseEntity
                .status(CREATED)
                .contentType(APPLICATION_JSON)
                .body(response);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ExamResponse> handleGetExamById(@RequestParam String examId) {
        ExamResponse response = getExamByIdUseCase.execute(examId);

        return ResponseEntity
                .status(OK)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
