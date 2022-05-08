package com.challenge.school.modules.template.builders;

import com.challenge.school.modules.template.entities.Template;
import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class TemplateBuilder {
    @Builder.Default
    private UUID testId = UUID.fromString("718d58fe-cfea-4940-9d65-a1bb4af42a40");

    @Builder.Default
    private List<TemplateAnswer> answers = buildAnswers();

    private static List<TemplateAnswer> buildAnswers() {
        TemplateAnswer firstAnswer = new TemplateAnswer(
                "Qual a cor do cavalo branco de Napoleão?",
                "A",
                3
        );
        TemplateAnswer secondAnswer = new TemplateAnswer(
                "Quem descobriu o Brasil?",
                "B",
                3
        );
        TemplateAnswer thirdAnswer = new TemplateAnswer(
                "Em que linguagem essa aplicação foi construída?",
                "D",
                3
        );

        return new ArrayList<>(List.of(firstAnswer, secondAnswer, thirdAnswer));
    }

    public Template buildTemplate() {
        return new Template(null, testId, answers);
    }
}
