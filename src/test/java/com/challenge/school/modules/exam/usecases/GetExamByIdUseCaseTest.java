package com.challenge.school.modules.exam.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.builders.ExamBuilder;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.builders.StudentBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetExamByIdUseCaseTest {
    @Mock
    ExamRepository examRepository;

    @InjectMocks
    GetExamByIdUseCase systemUnderTest;

    ExamBuilder examBuilder;
    StudentBuilder studentBuilder;

    @BeforeEach
    void setUp() {
        examBuilder = ExamBuilder.builder().build();
        studentBuilder = StudentBuilder.builder().build();
    }

    @Test
    void givenValidIdWhenGetExamByIdSearchIsCalledThenExamShouldBeReturned() {
        Student student = studentBuilder.buildStudent();
        Exam exam = examBuilder.buildExam(student);
        UUID id = UUID.randomUUID();

        when(examRepository.findById(id)).thenReturn(Optional.ofNullable(exam));
        Exam created = systemUnderTest.search(String.valueOf(id));

        assertThat(created).isNotNull();
        assertThat(created.getExamName()).isSameAs(exam.getExamName());
    }

    @Test
    void givenValidIdWhenGetExamByIdExecuteIsCalledThenExamResponseShouldBeReturned() {
        Student student = studentBuilder.buildStudent();
        Exam exam = examBuilder.buildExam(student);
        UUID id = UUID.randomUUID();

        when(examRepository.findById(id)).thenReturn(Optional.ofNullable(exam));
        ExamResponse created = systemUnderTest.execute(String.valueOf(id));

        assertThat(created).isNotNull();
        assertThat(created.getExamName()).isSameAs(exam.getExamName());
    }

    @Test
    void givenInvalidIdWhenGetExamByIdExecuteIsCalledThenCustomNotFoundShouldBeThrown() {
        UUID id = UUID.randomUUID();
        String errorMessage = String.format("Não foi possível encontrar exame com o id %s", id);

        when(examRepository.findById(id)).thenThrow(new CustomNotFoundException(errorMessage));

        assertThrows(
                CustomNotFoundException.class,
                () -> systemUnderTest.execute(String.valueOf(id))
        );
    }

    @Test
    void givenInvalidIdWhenGetExamByIdSearchIsCalledThenCustomNotFoundShouldBeThrown() {
        UUID id = UUID.randomUUID();
        String errorMessage = String.format("Não foi possível encontrar exame com o id %s", id);

        when(examRepository.findById(id)).thenThrow(new CustomNotFoundException(errorMessage));

        assertThrows(
                CustomNotFoundException.class,
                () -> systemUnderTest.search(String.valueOf(id))
        );
    }
}