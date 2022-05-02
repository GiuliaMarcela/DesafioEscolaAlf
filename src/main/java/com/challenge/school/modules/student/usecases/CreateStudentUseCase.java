package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.exceptions.CustomInternalServerException;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentMapper;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateStudentUseCase {
    private final StudentRepository repository;
    private static final StudentMapper mapper = StudentMapper.INSTANCE;

    private void checkIfStudentAlreadyExists(String email) {
        repository
                .findByEmail(email)
                .ifPresent(student -> {
                    String message = String.format("Usuário com email %s já foi cadastrado.", student.getEmail());

                    throw new CustomBadRequestException(message);
                });
    }

    private void checkIfCanStillRegisterStudents() {
        if (repository.findAll().size() >= 100) {
            throw new CustomBadRequestException("Não é possível cadastrar mais usuários.");
        }
    }

    private String generateEnrollmentForStudent() {
        try {
            Random random = SecureRandom.getInstanceStrong();
            Integer number = random.nextInt(100000);

            return String.format("%06d", number);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            log.error("Exception Message: " + noSuchAlgorithmException.getMessage());

            throw new CustomInternalServerException(
                    "Internal server error: Não foi possível gerar uma matrícula",
                    noSuchAlgorithmException
            );
        }
    }

    public StudentResponse execute(StudentRequest data) {
        checkIfCanStillRegisterStudents();
        checkIfStudentAlreadyExists(data.getEmail());

        Student student = new Student();

        student.setEnrollment(generateEnrollmentForStudent());
        student.setName(data.getName());
        student.setEmail(data.getEmail());

        repository.save(student);

        return mapper.mapToStudentResponse(student);
    }
}
