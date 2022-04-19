package com.challenge.school.modules.exam.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamMapper;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.Student;
import com.challenge.school.modules.student.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateExamUseCase {
    private static final ExamMapper mapper = ExamMapper.INSTANCE;

    private final ExamRepository repository;
    private final StudentRepository studentRepository;

    public ExamResponse execute(ExamRequest examRequest) {
        Student student = studentRepository
                .findByEnrollment(examRequest.getStudentEnrollment())
                .orElseThrow(() -> new CustomNotFoundException("Não foi possível encontrar aluno com a matrícula " + examRequest.getStudentEnrollment()));

        Exam exam = new Exam();

        exam.setExamName(examRequest.getExamName());
        exam.setStudent(student);
        exam.setAnswers(examRequest.getAnswers());
        exam.setGrade(null);

        repository.save(exam);
        return mapper.toExamResponse(exam);
    }
}
