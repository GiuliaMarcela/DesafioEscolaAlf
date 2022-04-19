package com.challenge.school.modules.exam;

import com.challenge.school.modules.exam.entities.Exam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {
}
