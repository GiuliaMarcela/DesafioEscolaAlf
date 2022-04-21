package com.challenge.school.modules.template;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.template.builders.TemplateRequestBuilder;
import com.challenge.school.modules.template.builders.TemplateResponseBuilder;
import com.challenge.school.modules.template.controllers.TemplateController;
import com.challenge.school.modules.template.dto.TemplateRequest;
import com.challenge.school.modules.template.dto.TemplateResponse;
import com.challenge.school.modules.template.usecases.CreateTemplateUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemplateController.class)
public class TemplateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreateTemplateUseCase createTemplateUseCase;

    TemplateRequestBuilder templateRequestBuilder;
    TemplateResponseBuilder templateResponseBuilder;

    @BeforeEach
    void setUp() {
        templateRequestBuilder = TemplateRequestBuilder.builder().build();
        templateResponseBuilder = TemplateResponseBuilder.builder().build();
    }

    @Test
    void whenPostIsCalledThenCreatedStatusShouldBeReturned() throws Exception {
        TemplateResponse templateResponse = templateResponseBuilder.buildResponse();
        TemplateRequest templateRequest = templateRequestBuilder.buildRequest();

        when(createTemplateUseCase.execute(templateRequest)).thenReturn(templateResponse);

        mockMvc.perform(post("/api/v1/templates")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(templateResponse))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.testId").isNotEmpty())
                .andExpect(jsonPath("$.answers").isNotEmpty())
                .andDo(print());
    }
}
