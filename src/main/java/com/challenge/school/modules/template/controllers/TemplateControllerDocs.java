package com.challenge.school.modules.template.controllers;

import com.challenge.school.modules.template.dto.TemplateRequest;
import com.challenge.school.modules.template.dto.TemplateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

@Tag(name = "Gabaritos", description = "Gerenciamento de gabaritos")
public interface TemplateControllerDocs {

    @Operation(
            method = "POST",
            summary = "Salvar um gabarito no banco de dados",
            description = "Salvar um gabarito no banco de dados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Campos obrigatórios inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Exame não foi encontrado"
            )
    })
    ResponseEntity<TemplateResponse> save(
            @RequestBody(required = true, description = "Informações necessárias para cadastrar um gabarito")
            TemplateRequest data
    );
}
