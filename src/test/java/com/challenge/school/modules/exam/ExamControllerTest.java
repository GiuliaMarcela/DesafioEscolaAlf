package com.challenge.school.modules.exam;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.builders.ExamRequestBuilder;
import com.challenge.school.modules.exam.builders.ExamResponseBuilder;
import com.challenge.school.modules.exam.controllers.ExamController;
import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.usecases.CreateExamUseCase;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExamController.class)
class ExamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ExamResponseBuilder responseBuilder;
    ExamRequestBuilder requestBuilder;

    @MockBean
    CreateExamUseCase createExamUseCase;

    @BeforeEach
    void setUp() {
        responseBuilder = ExamResponseBuilder.builder().build();
        requestBuilder = ExamRequestBuilder.builder().build();
    }

    @Test
    void whenPostIsCalledWithValidFieldsThenCreatedStatusShouldBeReturned() throws Exception {
        ExamRequest examRequest = requestBuilder.buildExamRequest();
        ExamResponse examResponse = responseBuilder.buildExamResponse();

        when(createExamUseCase.execute(examRequest)).thenReturn(examResponse);

        mockMvc.perform(
                        post("/api/v1/exams")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(examRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(print());
    }

    @Test
    void whenPostIsCalledWithInvalidFieldsThenNotFoundShouldBeReturned() throws Exception {
        ExamRequest examRequest = requestBuilder.buildExamRequest();
        String message = String.format(
                "Não foi possível encontrar aluno com a matrícula %s",
                examRequest.getStudentEnrollment()
        );

        when(createExamUseCase.execute(examRequest)).thenThrow(new CustomNotFoundException(message));

        mockMvc.perform(
                        post("/api/v1/exams")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(examRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.failed").value(true))
                .andExpect(jsonPath("$.message").value(message))
                .andDo(print());
    }
}
