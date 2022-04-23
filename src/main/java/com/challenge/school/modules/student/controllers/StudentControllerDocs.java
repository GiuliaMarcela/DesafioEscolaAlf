package com.challenge.school.modules.student.controllers;

import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import com.challenge.school.modules.student.dto.StudentRequest;
import com.challenge.school.modules.student.dto.StudentResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Estudantes", description = "Gerenciamento de estudantes")
public interface StudentControllerDocs {

    @Operation(
            method = "POST",
            summary = "Salvar um estudante no banco de dados",
            description = "Salvar um estudante no banco de dados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Campos obrigatórios ausentes ou estudante já registrado no sistema."
            )
    })
    ResponseEntity<StudentResponse> handleSave(
            @RequestBody(required = true, description = "Informações sobre o aluno como nome e e-mail")
            StudentRequest data
    );

    @Operation(
            method = "GET",
            summary = "Buscar um estudante pela matrícula no banco de dados",
            description = "Buscar um estudante pela matrícula no banco de dados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Campo obrigatório ausente ou errado."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não foi encontrado"
            )
    })
    ResponseEntity<StudentResponse> handleGetByEnrollment(
            @Parameter(required = true, description = "Matrícula do aluno a ser pesquisado")
            String enrollment
    );

    @Operation(
            method = "GET",
            summary = "Buscar a nota final de um aluno pela matrícula",
            description = "Buscar a nota final de um aluno pela matrícula"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Campo obrigatório ausente ou errado."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não foi encontrado"
            )
    })
    ResponseEntity<StudentFinalGradeResponse> handleGetFinalGrade(@RequestParam String enrollment);
}
