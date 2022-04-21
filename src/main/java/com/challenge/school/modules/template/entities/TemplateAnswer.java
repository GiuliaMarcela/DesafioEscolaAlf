package com.challenge.school.modules.template.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TemplateAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 100)
    private String question;

    @NotNull
    @Size(max = 100)
    private String correctAlternative;

    @NotNull
    @Min(1)
    @Max(9)
    private int weight;
}
