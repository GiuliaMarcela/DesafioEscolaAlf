package com.challenge.school.modules.student;

import com.challenge.school.modules.student.dto.StudentFinalGradeResponse;
import com.challenge.school.modules.student.dto.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    default StudentResponse mapToStudentResponse(Student model) {
        StudentResponse result = new StudentResponse();

        result.setId(model.getId());
        result.setEnrollment(model.getEnrollment());
        result.setName(model.getName());
        result.setEmail(model.getEmail());

        return result;
    }

    default StudentFinalGradeResponse fromStudentToStudentFinalGrade(Student model) {
        return new StudentFinalGradeResponse(
                model.getEnrollment(),
                model.getEmail(),
                model.getFinalGrade()
        );
    }
}
