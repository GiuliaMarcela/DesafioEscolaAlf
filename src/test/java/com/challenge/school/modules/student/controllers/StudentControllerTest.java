package com.challenge.school.modules.student.controllers;

import com.challenge.school.modules.student.builders.StudentRequestBuilder;
import com.challenge.school.modules.student.builders.StudentResponseBuilder;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;
import com.challenge.school.modules.student.usecases.CreateStudentUseCase;

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

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreateStudentUseCase createStudentUseCase;

    StudentRequestBuilder requestBuilder;
    StudentResponseBuilder responseBuilder;

    @BeforeEach
    void setUp() {
        requestBuilder = StudentRequestBuilder.builder().build();
        responseBuilder = StudentResponseBuilder.builder().build();
    }

    @Test
    void whenPostMethodIsCalledWithValidFieldsThenCreatedStatusShouldBeReturned() throws Exception {
        StudentRequest studentRequest = requestBuilder.buildStudentRequest();
        StudentResponse studentResponse = responseBuilder.buildStudentResponse();

        when(createStudentUseCase.execute(studentRequest)).thenReturn(studentResponse);

        mockMvc.perform(
                post("/api/v1/students")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.enrollment").isString())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.email").isString())
                .andDo(print());
    }
}