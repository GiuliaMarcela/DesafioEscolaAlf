package com.challenge.school.modules.exam.dto;

import com.challenge.school.modules.exam.entities.ExamAnswer;
import com.challenge.school.modules.student.dto.StudentResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String examName;
    private StudentResponse student;
    private transient List<ExamAnswer> answers;
}
