package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.student.Status;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.builders.StudentBuilder;
import com.challenge.school.modules.student.builders.StudentRequestBuilder;
import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStudentFinalGradeUseCaseTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private GetStudentFinalGradeUseCase systemUnderTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customNotFoundExceptionShouldBeThrownWhenStudentDoesNotExist() {
        String invalidEnrollment = "invalid_enrollment";
        String errorMessage = String.format(
                "Usuário referente a matrícula %s não foi encontrado.",
                invalidEnrollment
        );

        when(studentRepository.findByEnrollment(invalidEnrollment))
                .thenThrow(new CustomNotFoundException(errorMessage));

        assertThrows(CustomNotFoundException.class, () -> systemUnderTest.execute(invalidEnrollment));
    }

    @Test
    void customBadRequestExceptionShouldBeThrownWhenStudentHasNoRegisteredExams() {
        UUID id = UUID.fromString("b0343a22-439f-4ddf-8ac8-876505278a5a");
        String errorMessage = String.format(
                "Aluno %s ainda não tem nenhuma prova cadastrada.",
                "test"
        );

        when(examRepository.findByStudentId(id)).thenThrow(new CustomBadRequestException(errorMessage));

        assertThrows(
                CustomBadRequestException.class,
                () -> examRepository.findByStudentId(id)
        );
    }
}