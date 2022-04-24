package com.challenge.school.modules.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByEnrollment(String enrollment);

    @Query(nativeQuery = true, value = "select * from students s where s.status = 0")
    Page<Student> findAllApprovedStudents(Pageable pageable);
}
