package com.challenge.school.modules.student;

import com.challenge.school.modules.student.builders.StudentBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StudentRepository systemUnderTest;

    StudentBuilder studentBuilder;

    @BeforeEach
    void setUp() {
        studentBuilder = StudentBuilder.builder().build();
    }

    @Test
    void findByEmailResultShouldReturnTrueWhenValidEmailIsPassed() {
        Student student = studentBuilder.buildStudent();

        entityManager.persist(student);

        Optional<Student> result = systemUnderTest.findByEmail(student.getEmail());

        assertEquals(true, result.isPresent());
        assertThat(result.get().getEmail()).isEqualTo(student.getEmail());
    }

    @Test
    void findByEmailResultShouldReturnFalseWhenInvalidEmailIsPassed() {
        String email = "invalid";

        Optional<Student> result = systemUnderTest.findByEmail(email);

        assertEquals(false, result.isPresent());
    }

    @Test
    void findByEnrollmentShouldReturnTrueWhenValidEnrollmentIsProvided() {
        Student student = studentBuilder.buildStudent();

        entityManager.persist(student);

        Optional<Student> result = systemUnderTest.findByEnrollment(student.getEnrollment());

        Student response = result.get();

        assertEquals(true, result.isPresent());
        assertThat(response.getEnrollment()).isEqualTo(student.getEnrollment());
    }

    @Test
    void findByEnrollmentShouldReturnFalseWhenInvalidEnrollmentIsProvided() {
        String invalidEnrollment = "invalid-enrollment";

        Optional<Student> result = systemUnderTest.findByEnrollment(invalidEnrollment);

        assertEquals(true, result.isEmpty());
    }

    @Test
    void findAllApprovedStudentsShouldReturnStudentListWhenApprovedStudentsExists() {
        Student student = studentBuilder.buildStudent();
        Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());

        entityManager.persistAndFlush(student);

        Page<Student> approved = systemUnderTest.findAllApprovedStudents(pageable);

        assertThat(approved.getTotalElements()).isEqualTo(1);
    }

    @Test
    void findAllApprovedStudentsShouldReturnEmptyListWhenThereNoApprovedStudents() {
        Page<Student> approved = systemUnderTest.findAllApprovedStudents(isA(Pageable.class));

        assertThat(approved.getSize()).isEqualTo(0);
    }
}