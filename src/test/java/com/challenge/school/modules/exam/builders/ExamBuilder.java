package com.challenge.school.modules.exam.builders;

import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.exam.entities.ExamAnswer;
import com.challenge.school.modules.student.Student;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ExamBuilder {
    @Builder.Default
    private String examName = "Data JPA Tests";

    @Builder.Default
    private Integer grade = 9;

    @Builder.Default
    private List<ExamAnswer> answers = buildAnswers();

    private static List<ExamAnswer> buildAnswers() {
        ExamAnswer firstAnswer = new ExamAnswer("Qual a cor do cavalo branco de Napoleão?", "A");
        ExamAnswer secondAnswer = new ExamAnswer("Quem descobriu o Brasil?", "B");
        ExamAnswer thirdAnswer = new ExamAnswer("Em que linguagem essa aplicação foi construída?", "D");

        return new ArrayList<>(List.of(firstAnswer, secondAnswer, thirdAnswer));
    }

    public Exam buildExam(Student student) {
        return Exam.builder()
                .examName(examName)
                .student(student)
                .grade(grade)
                .answers(answers)
                .build();
    }
}
