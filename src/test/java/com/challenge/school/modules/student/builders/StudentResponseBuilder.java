package com.challenge.school.modules.student.builders;

import com.challenge.school.modules.student.dto.StudentResponse;
import lombok.Builder;

import java.util.UUID;

@Builder
public class StudentResponseBuilder {
    @Builder.Default private UUID id = UUID.fromString("b0343a22-439f-4ddf-8ac8-876505278a5a");
    @Builder.Default private String enrollment = "006368";
    @Builder.Default private String name = "John Bender";
    @Builder.Default private String email = "john.bender@test.com";

    public StudentResponse buildStudentResponse() {
        return new StudentResponse(id, enrollment, name, email);
    }
}
