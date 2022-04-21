package com.challenge.school.modules.template.dto;

import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String testId;
    private List<TemplateAnswer> answers;
}
