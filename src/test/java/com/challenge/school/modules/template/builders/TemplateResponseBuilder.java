package com.challenge.school.modules.template.builders;

import com.challenge.school.modules.template.dto.TemplateResponse;
import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class TemplateResponseBuilder {
    @Builder.Default
    private UUID id = UUID.fromString("f1b9b98b-7298-47b2-9778-c9c0a3101f2e");

    @Builder.Default
    private UUID testId = UUID.fromString("e64ead0f-8dc3-45dd-868f-94fb4c23da91");

    @Builder.Default
    private List<TemplateAnswer> answers = buildAnswers();

    private static List<TemplateAnswer> buildAnswers() {
        List<TemplateAnswer> templateAnswers = new ArrayList<>();

        TemplateAnswer firstAnswer = new TemplateAnswer("Qual a cor do cavalo branco de Napoleão?", "A", 3);
        TemplateAnswer secondAnswer = new TemplateAnswer("Quem descobriu o Brasil?", "B", 3);
        TemplateAnswer thirdAnswer = new TemplateAnswer("Em que linguagem essa aplicação foi construída?", "D", 3);

        templateAnswers.addAll(List.of(firstAnswer, secondAnswer, thirdAnswer));
        return templateAnswers;
    }

    public TemplateResponse buildResponse() {
        return new TemplateResponse(id, testId, answers);
    }
}
