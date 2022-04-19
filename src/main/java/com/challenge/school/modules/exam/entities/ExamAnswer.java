package com.challenge.school.modules.exam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 100)
    private String question;

    @NotNull @Size(min = 1, max = 3)
    private String alternative;
}
