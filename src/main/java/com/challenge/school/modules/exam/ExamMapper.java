package com.challenge.school.modules.exam;

import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.student.StudentMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);
    StudentMapper STUDENT_MAPPER = StudentMapper.INSTANCE;

    default ExamResponse toExamResponse(Exam model) {
        return new ExamResponse(
                model.getId(),
                model.getExamName(),
                STUDENT_MAPPER.mapToStudentResponse(model.getStudent()),
                model.getAnswers()
        );
    }
}
