package com.challenge.school.modules.student.usecases;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.Status;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentMapper;
import com.challenge.school.modules.student.StudentRepository;

import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetStudentFinalGradeUseCase {
    private final StudentRepository repository;
    private final ExamRepository examRepository;
    private static final StudentMapper mapper = StudentMapper.INSTANCE;

    private Student checkIfStudentExists(String enrollment) {
        return repository
                .findByEnrollment(enrollment)
                .orElseThrow(() -> new CustomNotFoundException("Nao foi possivel encontrar o aluno com a matricula " + enrollment));
    }

    public StudentFinalGradeResponse execute(String enrollment) {
        Student student = checkIfStudentExists(enrollment);

        List<Exam> exams = examRepository.findByStudentId(student.getId());
        Integer numberOfTestsDone = exams.size();
        Integer totalGrades = 0;

        if (exams.isEmpty()) {
            String message = String.format("%s ainda não tem nenhuma prova cadastrada.", student.getEmail());

            throw new CustomBadRequestException(message);
        }

        for (Exam exam : exams) {
            totalGrades = totalGrades + exam.getGrade();
        }

        Integer average = totalGrades / numberOfTestsDone;

        student.setFinalGrade(average);

        if (average > 7) {
            student.setStatus(Status.APPROVED);
        } else {
            student.setStatus(Status.DISAPPROVED);
        }

        repository.save(student);

        return mapper.fromStudentToStudentFinalGrade(student);
    }
}
