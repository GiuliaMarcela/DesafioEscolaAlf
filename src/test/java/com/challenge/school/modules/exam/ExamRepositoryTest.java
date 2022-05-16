package com.challenge.school.modules.exam;

import com.challenge.school.modules.exam.builders.ExamBuilder;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.builders.StudentBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExamRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ExamRepository systemUnderTest;

    ExamBuilder examBuilder;
    StudentBuilder studentBuilder;

    @BeforeEach
    void setUp() {
        examBuilder = ExamBuilder.builder().build();
        studentBuilder = StudentBuilder.builder().build();
    }

    @Test
    void findByIdShouldReturnExamWhenValidIdIsProvided() {
        Student student = entityManager.persistAndFlush(studentBuilder.buildStudent());

        Exam buildExam = examBuilder.buildExam(student);
        Exam result = entityManager.persistAndFlush(buildExam);

        Optional<Exam> examOptional = systemUnderTest.findById(result.getId());

        assertThat(examOptional.get().getId()).isInstanceOf(UUID.class);
        assertThat(examOptional.get().getId()).isEqualTo(result.getId());
    }

    @Test
    void findByIdShouldReturnFalseWhenInvalidIdIsProvided() {
        Optional<Exam> examOptional = systemUnderTest.findById(UUID.randomUUID());

        assertThat(examOptional).isNotPresent();
    }

    @Test
    void saveShouldReturnExamWhenValidExamIsProvided() {
        Student student = entityManager.persistAndFlush(studentBuilder.buildStudent());
        Exam expected = examBuilder.buildExam(student);

        Exam result = systemUnderTest.save(expected);

        assertThat(expected.getExamName()).isEqualTo(result.getExamName());
        assertThat(expected.getAnswers()).isEqualTo(result.getAnswers());
        assertThat(expected.getGrade()).isEqualTo(result.getGrade());
    }
}