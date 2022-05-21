package com.challenge.school.modules.exam.builders;

import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.entities.ExamAnswer;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ExamRequestBuilder {
    @Builder.Default
    private String examName = "English";

    @Builder.Default
    private String studentEnrollment = "006368";

    @Builder.Default
    private List<ExamAnswer> answers = buildAnswers();

    private static List<ExamAnswer> buildAnswers() {
        List<ExamAnswer> examAnswers = new ArrayList<>();

        ExamAnswer firstAnswer = new ExamAnswer("Qual a cor do cavalo branco de Napoleão?", "A");
        ExamAnswer secondAnswer = new ExamAnswer("Quem descobriu o Brasil?", "B");
        ExamAnswer thirdAnswer = new ExamAnswer("Em que linguagem essa aplicação foi construída?", "D");

        examAnswers.addAll(List.of(firstAnswer, secondAnswer, thirdAnswer));
        return examAnswers;
    }

    public ExamRequest buildExamRequest() {
        return new ExamRequest(examName, studentEnrollment, answers);
    }
}
