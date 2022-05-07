package com.challenge.school.modules.student;

import com.challenge.school.modules.student.builders.StudentBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}