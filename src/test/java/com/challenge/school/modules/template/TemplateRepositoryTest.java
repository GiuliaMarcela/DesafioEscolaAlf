package com.challenge.school.modules.template;

import com.challenge.school.modules.template.builders.TemplateBuilder;
import com.challenge.school.modules.template.entities.Template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TemplateRepositoryTest {
    @Autowired
    TemplateRepository systemUnderTest;

    TemplateBuilder templateBuilder;

    @BeforeEach
    void setUp() {
        templateBuilder = TemplateBuilder.builder().build();
    }

    @Test
    void saveShouldReturnTemplateWhenValidTemplateIsProvided() {
        Template template = templateBuilder.buildTemplate();

        Template result = systemUnderTest.save(template);

        assertThat(result.getId()).isInstanceOf(UUID.class);
        assertThat(template.getTestId()).isEqualTo(result.getTestId());
        assertThat(template.getAnswers()).isEqualTo(result.getAnswers());
    }
}