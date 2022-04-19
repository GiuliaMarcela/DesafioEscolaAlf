package com.challenge.school.modules.exam.dto;

import com.challenge.school.modules.exam.entities.ExamAnswer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String examName;
    private String studentEnrollment;
    private transient List<ExamAnswer> answers;
}
