package com.challenge.school.modules.template.dto;

import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID testId;
    private List<TemplateAnswer> answers;
}
