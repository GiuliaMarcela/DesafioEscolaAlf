package com.challenge.school.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFinalGradeResponse {
    private String enrollment;
    private String email;
    private Integer finalGrade;
}
