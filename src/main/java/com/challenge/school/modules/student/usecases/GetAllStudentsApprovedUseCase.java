package com.challenge.school.modules.student.usecases;

import com.challenge.school.modules.student.StudentMapper;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllStudentsApprovedUseCase {
    private final StudentRepository repository;
    private static final StudentMapper mapper = StudentMapper.INSTANCE;

    public Page<StudentResponse> execute(Pageable pageable) {
        return repository
                .findAllApprovedStudents(pageable)
                .map(mapper::mapToStudentResponse);
    }
}
