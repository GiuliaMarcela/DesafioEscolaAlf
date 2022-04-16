package com.challenge.school.modules.student.usecases;

import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentMapper;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

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

                    throw new RuntimeException(message);
                });
    }

    private void checkIfCanStillRegisterStudents() {
        if (repository.findAll().size() >= 100) {
            throw new RuntimeException("Não é possível cadastrar mais usuários.");
        }
    }

    private String generateEnrollmentForStudent() {
        Random random = new Random();
        Integer number = random.nextInt(100000);

        return String.format("%06d", number);
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
