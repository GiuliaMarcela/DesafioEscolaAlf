package com.challenge.school.modules.template.controllers;

import com.challenge.school.modules.template.dto.TemplateRequest;
import com.challenge.school.modules.template.dto.TemplateResponse;
import com.challenge.school.modules.template.usecases.CreateTemplateUseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${api.prefix}/${api.version}/templates")
public class TemplateController implements TemplateControllerDocs {
    private final CreateTemplateUseCase createTemplateUseCase;

    @Override
    @PostMapping
    public ResponseEntity<TemplateResponse> save(@RequestBody TemplateRequest data) {
        TemplateResponse response = createTemplateUseCase.execute(data);

        return ResponseEntity
                .status(CREATED)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
