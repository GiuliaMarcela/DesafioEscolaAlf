package com.challenge.school.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI buildSwaggerDocumentation() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Escola ALF")
                                .description(
                                        "A escola Alf aplica provas de múltipla escolha para os alunos. " +
                                                "A nota do aluno na prova é determinada pela média ponderada " +
                                                "das questões com os pesos de cada questão."
                                )
                                .version("0.0.1-SNAPSHOT")
                                .contact(
                                        new Contact()
                                                .name("Giulia Marcela")
                                                .email("giuliamendes67@gmail.com")
                                                .url("https://linkedin.com/in/giuliamendes67")
                                )
                );
    }
}
