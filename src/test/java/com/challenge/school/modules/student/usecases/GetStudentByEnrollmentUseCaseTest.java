package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.builders.StudentBuilder;
import com.challenge.school.modules.student.builders.StudentResponseBuilder;
import com.challenge.school.modules.student.dto.StudentResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStudentByEnrollmentUseCaseTest {

    @InjectMocks
    GetStudentByEnrollmentUseCase systemUnderTest;

    @Mock
    StudentRepository studentRepository;

    StudentResponseBuilder studentResponseBuilder;
    StudentBuilder studentBuilder;

    @BeforeEach
    void setUp() {
        studentBuilder = StudentBuilder.builder().build();
        studentResponseBuilder = StudentResponseBuilder.builder().build();
    }

    @Test
    void studentShouldBeReturnedWhenValidEnrollmentIsProvided() {
        String validEnrollment = "006368";
        Student student = studentBuilder.buildStudent();

        when(studentRepository.findByEnrollment(validEnrollment)).thenReturn(Optional.of(student));
        StudentResponse studentFound = systemUnderTest.execute(validEnrollment);

        assertThat(studentFound)
                .hasFieldOrProperty("enrollment")
                .hasFieldOrPropertyWithValue("enrollment", validEnrollment);
    }

    @Test
    void customNotFoundExceptionShouldBeThrownWhenInvalidEnrollmentIsProvided() {
        String invalidEnrollment = "invalid_enrollment";
        String errorMessage = String.format(
                "Usuário referente a matrícula %s não foi encontrado.",
                invalidEnrollment
        );

        when(studentRepository.findByEnrollment(invalidEnrollment))
                .thenThrow(new CustomNotFoundException(errorMessage));

        assertThrows(CustomNotFoundException.class, () -> systemUnderTest.execute(invalidEnrollment));
    }
}