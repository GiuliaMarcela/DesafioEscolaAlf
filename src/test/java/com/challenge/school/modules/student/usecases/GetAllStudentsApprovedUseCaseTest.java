package com.challenge.school.modules.student.usecases;

import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;
import com.challenge.school.modules.student.builders.StudentBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllStudentsApprovedUseCaseTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    GetAllStudentsApprovedUseCase systemUnderTest;
    StudentBuilder studentBuilder;

    @BeforeEach
    void setUp() {
        studentBuilder = StudentBuilder.builder().build();
    }

    @Test
    void shouldReturnPageWithStudentsWhenThereAreApprovedStudents() {
        Student student = studentBuilder.buildStudent();
        Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());
        var pages = new PageImpl(new ArrayList<>(List.of(student)), pageable, 1);

        when(studentRepository.findAllApprovedStudents(pageable)).thenReturn(pages);
        var created = systemUnderTest.execute(pageable);

        assertThat(created).isNotEmpty();
    }

    @Test
    void shouldReturnAnEmptyPageWhenThereNoApprovedStudents() {
        Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());
        var pages = new PageImpl(new ArrayList<>(), pageable, 1);

        when(studentRepository.findAllApprovedStudents(pageable)).thenReturn(pages);
        var created = systemUnderTest.execute(pageable);

        assertThat(created).isEmpty();
    }
}