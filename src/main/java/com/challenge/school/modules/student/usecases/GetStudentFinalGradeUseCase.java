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
                .orElseThrow(() -> new CustomNotFoundException(
                        "Nao foi possivel encontrar o aluno com a matricula " + enrollment
                ));
    }

    private int calculateStudentFinalGrade(Student actualStudent) {
        List<Exam> exams = examRepository.findByStudentId(actualStudent.getId());

        Integer numberOfTestsDone = exams.size();
        Integer totalGrades = 0;

        if (exams.isEmpty()) {
            String errorMessage = String.format("Aluno %s ainda não tem nenhuma prova cadastrada.", actualStudent.getEmail());

            throw new CustomBadRequestException(errorMessage);
        }

        for (Exam exam : exams) {
            totalGrades = totalGrades + exam.getGrade();
        }

        return totalGrades / numberOfTestsDone;
    }

    public StudentFinalGradeResponse execute(String enrollment) {
        Student student = checkIfStudentExists(enrollment);

        int studentFinalGradeAverage = calculateStudentFinalGrade(student);

        if (studentFinalGradeAverage <= 7) {
            student.setStatus(Status.DISAPPROVED);
        }

        student.setStatus(Status.APPROVED);
        student.setFinalGrade(studentFinalGradeAverage);
        repository.save(student);

        return mapper.fromStudentToStudentFinalGrade(student);
    }
}
