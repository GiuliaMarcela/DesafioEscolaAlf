package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentMapper;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStudentByEnrollmentUseCase {
    private final StudentRepository repository;
    private static final StudentMapper mapper = StudentMapper.INSTANCE;

    public StudentResponse execute(String enrollment) {
        String message = String.format("Usuário referente a matrícula %s não foi encontrado.", enrollment);

        Student student = repository
                .findByEnrollment(enrollment)
                .orElseThrow(() -> new CustomNotFoundException(message));

        return mapper.mapToStudentResponse(student);
    }
}
