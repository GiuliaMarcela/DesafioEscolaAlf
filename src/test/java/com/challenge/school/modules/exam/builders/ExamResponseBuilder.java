package com.challenge.school.modules.exam.builders;

import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.ExamAnswer;
import com.challenge.school.modules.student.builders.StudentResponseBuilder;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class ExamResponseBuilder {
    @Builder.Default
    private UUID id = UUID.fromString("718d58fe-cfea-4940-9d65-a1bb4af42a40");

    @Builder.Default
    private String examName = "English";

    @Builder.Default
    private StudentResponse student = StudentResponseBuilder.builder().build().buildStudentResponse();

    @Builder.Default
    private transient List<ExamAnswer> answers = buildAnswers();

    private static List<ExamAnswer> buildAnswers() {
        List<ExamAnswer> examAnswers = new ArrayList<>();

        ExamAnswer firstAnswer = new ExamAnswer("Qual a cor do cavalo branco de Napoleão?", "A");
        ExamAnswer secondAnswer = new ExamAnswer("Quem descobriu o Brasil?", "B");
        ExamAnswer thirdAnswer = new ExamAnswer("Em que linguagem essa aplicação foi construída?", "D");

        examAnswers.addAll(List.of(firstAnswer, secondAnswer, thirdAnswer));
        return examAnswers;
    }

    public ExamResponse buildExamResponse() {
        return new ExamResponse(id, examName, student, answers);
    }
}
