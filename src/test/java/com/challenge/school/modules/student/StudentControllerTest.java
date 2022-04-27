package com.challenge.school.modules.student;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.student.builders.StudentFinalGradeBuilder;
import com.challenge.school.modules.student.builders.StudentRequestBuilder;
import com.challenge.school.modules.student.builders.StudentResponseBuilder;
import com.challenge.school.modules.student.controllers.StudentController;
import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;
import com.challenge.school.modules.student.usecases.CreateStudentUseCase;
import com.challenge.school.modules.student.usecases.GetAllStudentsApprovedUseCase;
import com.challenge.school.modules.student.usecases.GetStudentByEnrollmentUseCase;

import com.challenge.school.modules.student.usecases.GetStudentFinalGradeUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
    GetStudentByEnrollmentUseCase getStudentByEnrollmentUseCase;

    @MockBean
    GetStudentFinalGradeUseCase getStudentFinalGradeUseCase;

    @MockBean
    GetAllStudentsApprovedUseCase getAllStudentsApprovedUseCase;

    StudentRequestBuilder requestBuilder;
    StudentResponseBuilder responseBuilder;
    StudentFinalGradeBuilder finalGradeBuilder;

    @BeforeEach
    void setUp() {
        requestBuilder = StudentRequestBuilder.builder().build();
        responseBuilder = StudentResponseBuilder.builder().build();
        finalGradeBuilder = StudentFinalGradeBuilder.builder().build();
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

    @Test
    void whenPostMethodIsCalledWithInvalidFieldsThenBadRequestStatusShouldBeReturned() throws Exception {
        StudentRequest studentRequest = requestBuilder.buildStudentRequest();
        String message = String.format("Usuário com email %s já foi cadastrado.", studentRequest.getEmail());

        when(createStudentUseCase.execute(studentRequest))
                .thenThrow(new CustomBadRequestException(message));

        mockMvc.perform(
                        post("/api/v1/students")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.failed").value(true))
                .andExpect(jsonPath("$.message").value(message))
                .andDo(print());
    }

    @Test
    void whenGetByEnrollmentIsCalledWithValidEnrollmentThenOkStatusShouldBeReturned() throws Exception {
        String enrollment = "006368";
        StudentResponse studentResponse = responseBuilder.buildStudentResponse();

        when(getStudentByEnrollmentUseCase.execute(enrollment)).thenReturn(studentResponse);

        mockMvc.perform(
                        get("/api/v1/students?enrollment=" + studentResponse.getEnrollment())
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.enrollment").value(studentResponse.getEnrollment()))
                .andDo(print());
    }

    @Test
    void whenGetByEnrollmentIsCalledWithInvalidEnrollmentThenNotFoundStatusShouldBeReturned() throws Exception {
        String enrollment = "009021";
        String message = String.format("Usuário referente a matrícula %s não foi encontrado.", enrollment);

        when(getStudentByEnrollmentUseCase.execute(enrollment)).thenThrow(new CustomNotFoundException(message));

        mockMvc.perform(
                        get("/api/v1/students?enrollment=" + enrollment)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.failed").value(true))
                .andExpect(jsonPath("$.message").value(message))
                .andDo(print());
    }

    @Test
    void whenGetFinalGradeIsCalledThenOkStatusShouldBeReturned() throws Exception {
        String enrollment = "009021";
        StudentFinalGradeResponse studentFinalGradeResponse = finalGradeBuilder.buildStudentFinalGrade();

        when(getStudentFinalGradeUseCase.execute(enrollment)).thenReturn(studentFinalGradeResponse);

        mockMvc.perform(
                        get("/api/v1/students/grade?enrollment=" + enrollment)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.enrollment").value(studentFinalGradeResponse.getEnrollment()))
                .andExpect(jsonPath("$.email").value(studentFinalGradeResponse.getEmail()))
                .andExpect(jsonPath("$.finalGrade").value(studentFinalGradeResponse.getFinalGrade()))
                .andDo(print());
    }

    @Test
    void whenGetAllStudentApprovedIsCalledThenOkStatusShouldBeReturned() throws Exception {
        StudentResponse studentResponse = responseBuilder.buildStudentResponse();
        List<StudentResponse> studentResponseList = new ArrayList<>(List.of(studentResponse));

        Page<StudentResponse> studentResponsePage = new PageImpl<>(studentResponseList);
        when(getAllStudentsApprovedUseCase.execute(isA(Pageable.class)))
                .thenReturn(studentResponsePage);

        mockMvc.perform(
                        get("/api/v1/students/approved")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andDo(print());
    }

    @Test
    void whenThereAreNoApprovedStudentsAnEmptyListShouldBeReturnedWithOkStatus() throws Exception {
        List<StudentResponse> emptyStudentResponse = new ArrayList<>();
        Page<StudentResponse> studentResponsePage = new PageImpl<>(emptyStudentResponse);

        when(getAllStudentsApprovedUseCase.execute(isA(Pageable.class))).thenReturn(studentResponsePage);

        mockMvc.perform(
                        get("/api/v1/students/approved")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").isEmpty())
                .andDo(print());
    }
}
