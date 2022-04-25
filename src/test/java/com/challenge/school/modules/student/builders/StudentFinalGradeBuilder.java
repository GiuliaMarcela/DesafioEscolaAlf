package com.challenge.school.modules.student.builders;

import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import lombok.Builder;

@Builder
public class StudentFinalGradeBuilder {

    @Builder.Default private String enrollment = "006368";
    @Builder.Default private String email = "john.bender@test.com";
    @Builder.Default private Integer finalGrade = 9;

    public StudentFinalGradeResponse buildStudentFinalGrade() {
        return new StudentFinalGradeResponse(enrollment, email, finalGrade);
    }
}
