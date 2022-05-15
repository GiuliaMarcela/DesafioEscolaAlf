package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.builders.StudentBuilder;
import com.challenge.school.modules.student.builders.StudentRequestBuilder;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentUseCaseTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    CreateStudentUseCase createStudentUseCase;

    StudentBuilder studentBuilder;
    StudentRequestBuilder requestBuilder;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        studentBuilder = StudentBuilder.builder().build();
        requestBuilder = StudentRequestBuilder.builder().build();

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void shouldCreateStudentWhenMethodIsCalledWithValidFieldsIsProvided() {
        StudentRequest studentRequest = requestBuilder.buildStudentRequest();

        when(studentRepository.save(any(Student.class))).thenReturn(new Student());

        StudentResponse created = createStudentUseCase.execute(studentRequest);

        assertThat(studentRequest.getEmail()).isSameAs(created.getEmail());
    }

    @Test
    void customBadRequestExceptionShouldBeThrownWhenTheListOfStudentsExceeds100() {
        StudentRequest hundredOne = requestBuilder.buildStudentRequest();
        String errorMessage = "Não é possível cadastrar mais usuários.";

        when(studentRepository.save(any(Student.class))).thenThrow(new CustomBadRequestException(errorMessage));

        assertThrows(CustomBadRequestException.class, () -> createStudentUseCase.execute(hundredOne));
    }

    @Test
    void customBadRequestExceptionShouldBeThrownWhenEmailAlreadyRegisteredIsProvided() {
        StudentRequest emailAlreadyRegistered = requestBuilder.buildStudentRequest();
        String errorMessage = String.format("Usuário com email %s já foi cadastrado.", emailAlreadyRegistered.getEmail());

        when(studentRepository.save(any(Student.class))).thenThrow(new CustomBadRequestException(errorMessage));

        assertThrows(CustomBadRequestException.class, () -> createStudentUseCase.execute(emailAlreadyRegistered));
    }

    @Test
    void shouldReturnTrueWhenThereAreMissingFieldsExisting() {
        StudentRequest studentRequest = StudentRequest.builder()
                .name("Missing email").build();

        var violations = validator.validate(studentRequest);
        assertFalse(violations.isEmpty());
    }
}