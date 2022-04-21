package com.challenge.school.modules.template.builders;

import com.challenge.school.modules.template.dto.TemplateRequest;
import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class TemplateRequestBuilder {
    @Builder.Default
    private String testId = "e64ead0f-8dc3-45dd-868f-94fb4c23da91";

    @Builder.Default
    private List<TemplateAnswer> answers = buildAnswers();

    private static List<TemplateAnswer> buildAnswers() {
        List<TemplateAnswer> templateAnswers = new ArrayList<>();

        TemplateAnswer firstAnswer = new TemplateAnswer(
                "Qual a cor do cavalo branco de Napoleão?",
                "A",
                3
        );

        TemplateAnswer secondAnswer = new TemplateAnswer("Quem descobriu o Brasil?", "B", 3);
        TemplateAnswer thirdAnswer = new TemplateAnswer(
                "Em que linguagem essa aplicação foi construída?",
                "D",
                3
        );

        templateAnswers.addAll(List.of(firstAnswer, secondAnswer, thirdAnswer));
        return templateAnswers;
    }

    public TemplateRequest buildRequest() {
        return new TemplateRequest(testId, answers);
    }
}
