package com.challenge.school.modules.student.builders;

import com.challenge.school.modules.student.dto.StudentRequest;
import lombok.Builder;

@Builder
public class StudentRequestBuilder {
    @Builder.Default
    private String name = "John Bender";

    @Builder.Default
    private String email = "john.bender@test.com";

    public StudentRequest buildStudentRequest() {
        return new StudentRequest(name, email);
    }
}
