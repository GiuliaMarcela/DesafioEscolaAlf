package com.challenge.school.modules.exam.controllers;

import com.challenge.school.modules.exam.dto.ExamRequest;
import com.challenge.school.modules.exam.dto.ExamResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

@Tag(name = "Exames", description = "Gerenciamento de exames")
public interface ExamControllerDocs {

    @Operation(
            method = "POST",
            summary = "Salvar um exame no banco de dados",
            description = "Salvar um exame no banco de dados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alguma informação não foi encontrada"
            )
    })
    ResponseEntity<ExamResponse> handleSaveExam(
            @RequestBody(required = true, description = "Informações necessárias para a cadastrar as respostas de um aluno.")
            ExamRequest data
    );
}
