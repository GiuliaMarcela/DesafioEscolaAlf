package com.challenge.school.modules.student.builders;

import com.challenge.school.modules.student.Status;
import com.challenge.school.modules.student.Student;

import lombok.Builder;

@Builder
public class StudentBuilder {
    @Builder.Default
    private String enrollment = "006368";

    @Builder.Default
    private String name = "John Bender";

    @Builder.Default
    private String email = "john@bender.com";

    @Builder.Default
    private Integer finalGrade = 8;

    @Builder.Default
    private Status status = Status.APPROVED;

    public Student buildStudent() {
        return Student.builder()
                .enrollment(enrollment)
                .name(name)
                .email(email)
                .finalGrade(finalGrade)
                .status(status)
                .build();
    }
}
