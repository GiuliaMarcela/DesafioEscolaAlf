package com.challenge.school.modules.exam.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.builders.ExamBuilder;
import com.challenge.school.modules.exam.builders.ExamRequestBuilder;
import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.builders.StudentBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateExamUseCaseTest {

    @Mock
    ExamRepository examRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    CreateExamUseCase createExamUseCase;

    StudentBuilder studentBuilder;
    ExamBuilder examBuilder;
    ExamRequestBuilder examRequestBuilder;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        examBuilder = ExamBuilder.builder().build();
        studentBuilder = StudentBuilder.builder().build();
        examRequestBuilder = ExamRequestBuilder.builder().build();

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void customNotFoundExceptionShouldBeThrownWhenStudentWithGivenEnrollmentIsNotRegistered() {
        String invalidEnrollment = "invalidEnrollment";
        String errorMessage = String.format("Não foi possível encontrar aluno com a matrícula %s", invalidEnrollment);

        ExamRequest examRequest = examRequestBuilder.buildExamRequest();
        examRequest.setStudentEnrollment(invalidEnrollment);

        when(studentRepository.findByEnrollment(invalidEnrollment)).thenThrow(new CustomNotFoundException(errorMessage));

        assertThrows(
                CustomNotFoundException.class,
                () -> createExamUseCase.execute(examRequest)
        );
    }

    @Test
    void givenAllFieldsFilledCorrectlyWhenCreateExamUseCaseIsCalledThenAnExamShouldBeRegistered() {
        Student student = studentBuilder.buildStudent();

        Exam exam = examBuilder.buildExam(student);
        ExamRequest examRequest = examRequestBuilder.buildExamRequest();

        when(studentRepository.findByEnrollment(student.getEnrollment())).thenReturn(Optional.of(student));
        when(examRepository.save(any(Exam.class))).thenReturn(exam);
        ExamResponse response = createExamUseCase.execute(examRequest);

        assertEquals(examRequest.getExamName(), response.getExamName());
        assertEquals(examRequest.getStudentEnrollment(), response.getStudent().getEnrollment());
    }

    @Test
    void givenMissingFieldsWhenCreateExamUseCaseIsCalledThenMethodArgumentNotValidExceptionShouldBeThrown() {
        ExamRequest examRequestInvalid = examRequestBuilder.buildExamRequest();
        examRequestInvalid.setExamName(null);
        examRequestInvalid.setAnswers(null);

        var violations = validator.validate(examRequestInvalid);

        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
    }
}